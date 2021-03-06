package mx.brg.dibujandounmanana.ui.campanias

import android.app.ProgressDialog
import android.content.Context
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.databinding.ActivityCampaniaSeleccionadaBinding
import mx.brg.dibujandounmanana.model.CampaniaBD
import mx.brg.dibujandounmanana.model.CampaniaId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/*
Esta actividad muestra la información completa de la Campania a la que se hizo click en Ver Más
 */

class CampaniaSeleccionada : AppCompatActivity() {

    private lateinit var binding: ActivityCampaniaSeleccionadaBinding

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
        binding = ActivityCampaniaSeleccionadaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Cargando ...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val preferencias = this.getSharedPreferences("campaniaSeleccionada", Context.MODE_PRIVATE)
        val id = preferencias.getInt("id", -1)
        val call = servicioDibujandoApi.verCampania(CampaniaId(id))
        call.enqueue(object: Callback<CampaniaBD> {
            override fun onResponse(call: Call<CampaniaBD>, response: Response<CampaniaBD>) {
                if (response.isSuccessful)
                {
                    if (response.code() == 200)
                    {
                        binding.tvTituloCampaniaSeleccionada.setText(response.body()?.nombre)
                        binding.tvDescripcionCampaniaSeleccionada.setText(response.body()?.descripcion)
                        val storageRef = FirebaseStorage.getInstance().reference.child("campanias/${response.body()?.nombre}")
                        val file = File.createTempFile("tempImage", "jpg")
                        storageRef.getFile(file).addOnSuccessListener {

                            if (progressDialog.isShowing)
                            {
                                progressDialog.dismiss()
                            }

                            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                            binding.ivCampaniaSeleccionada.setImageBitmap(bitmap)

                        }.addOnFailureListener{
                            if (progressDialog.isShowing)
                            {
                                progressDialog.dismiss()
                            }

                            Toast.makeText(applicationContext, "No se recuperó la imagen", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else if (response.code() == 404)
                {
                    Toast.makeText(applicationContext, "No existen Campañas", Toast.LENGTH_SHORT).show()
                } else if (response.code() == 500)
                {
                    Toast.makeText(applicationContext, "Servidor caído", Toast.LENGTH_SHORT).show()
                } else {
                    println(response.errorBody())
                }
            }
            override fun onFailure(call: Call<CampaniaBD>, t: Throwable) {
                println("Error: ${t.localizedMessage}")
            }
        })
    }
}