package mx.brg.dibujandounmanana.admin

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.databinding.ActivityCrearCampaniasBinding
import mx.brg.dibujandounmanana.model.Campania
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CrearCampaniasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearCampaniasBinding
    private lateinit var imageUri: Uri
    private var imagen = false

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.64:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioDibujandoApi by lazy {
        retrofit.create(ServicioDibujandoApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearCampaniasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarObservadores()
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    private fun configurarObservadores() {
        binding.btnSelImagen.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED)
                {
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    pickImageFromGallery()
                }
            } else {
                pickImageFromGallery()
            }
        }

        binding.btnCrearIniciativa.setOnClickListener {
            if (imagen == true)
            {
                val progressDialog = ProgressDialog(this)
                progressDialog.setMessage("Subiendo imagen ...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val preferencias = this.getSharedPreferences("tokenAdmin", Context.MODE_PRIVATE)
                val token = preferencias.getString("token","none").toString()
                val nombre = binding.etNombreCampania.text.toString()
                val descripcion = binding.etDescripcionInciativa.text.toString()
                val campania = Campania(nombre, descripcion)
                val fileName = campania.nombre
                val storageReference = FirebaseStorage.getInstance().getReference("campanias/$fileName")
                storageReference.putFile(imageUri).addOnSuccessListener {
                    Toast.makeText(this, "Imagen subida correctamente", Toast.LENGTH_SHORT).show()
                    if (progressDialog.isShowing)
                    {
                        progressDialog.dismiss()
                    }
                }.addOnFailureListener{
                    Toast.makeText(this, "Algo falló con la imagen", Toast.LENGTH_SHORT).show()
                }

                val call = servicioDibujandoApi.crearCampania("Bearer $token", campania)
                call.enqueue(object: Callback<Map<String, String>> {
                    override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                        if (response.isSuccessful)
                        {
                            if (response.code() == 200)
                            {
                                println(response.body())
                            }
                        } else if (response.code() == 403)
                        {
                            println("Error:")
                            println(response.body())
                        } else if (response.code() == 500)
                        {
                            println("Servidor caído")
                            println(response.body())
                        } else {
                            println("Error: ${response.errorBody()}")
                        }
                    }

                    override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                        println("Error: ${t.localizedMessage}")
                    }

                })
            } else {
                Toast.makeText(applicationContext, "Selecciona una imagen", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(applicationContext, "Permiso denegado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE)
        {
            imageUri = data?.data!!
            imagen = true
        }
    }
}