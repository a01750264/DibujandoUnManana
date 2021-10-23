package mx.brg.dibujandounmanana

import android.app.ProgressDialog
import android.content.Context
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.databinding.ActivityCampaniaSeleccionadaBinding
import mx.brg.dibujandounmanana.databinding.ActivityIniciativaSeleccionadaBinding
import mx.brg.dibujandounmanana.model.IniciativaBD
import mx.brg.dibujandounmanana.model.IniciativaId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/*
Esta actividad muestra la información completa de la Campania a la que se hizo click en Ver Más,
y también está al pendiente de cuando se haga click en el botón de Participar
 */

class IniciativaSeleccionada : AppCompatActivity() {

    private lateinit var binding: ActivityIniciativaSeleccionadaBinding

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
        binding = ActivityIniciativaSeleccionadaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Cargando ...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val preferencias = this.getSharedPreferences("iniciativaSeleccionada", Context.MODE_PRIVATE)
        val id = preferencias.getInt("id", -1)
        val call = servicioDibujandoApi.verIniciativa(IniciativaId(id))
        call.enqueue(object: Callback<IniciativaBD> {
            override fun onResponse(call: Call<IniciativaBD>, response: Response<IniciativaBD>) {
                if (response.isSuccessful)
                {
                    if (response.code() == 200)
                    {
                        binding.tvTituloIniciativaSeleccionada.setText(response.body()?.nombre)
                        binding.tvDescripcionIniciativaSeleccionada.setText(response.body()?.descripcion)
                        val storageRef = FirebaseStorage.getInstance().reference.child("iniciativas/${response.body()?.nombre}${response.body()?.maxPart}")
                        val file = File.createTempFile("tempImage", "jpg")
                        storageRef.getFile(file).addOnSuccessListener {

                            if (progressDialog.isShowing)
                            {
                                progressDialog.dismiss()
                            }

                            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                            binding.ivIniciativaSeleccionada.setImageBitmap(bitmap)

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
                    Toast.makeText(applicationContext, "No existen Iniciativas", Toast.LENGTH_SHORT).show()
                } else if (response.code() == 500)
                {
                    Toast.makeText(applicationContext, "Servidor caído", Toast.LENGTH_SHORT).show()
                } else {
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: Call<IniciativaBD>, t: Throwable) {
                println("Error: ${t.localizedMessage}")
            }
        })

        configurarObservadores()
    }

    private fun configurarObservadores() {
        binding.btnParticiparIniciativa.setOnClickListener {
            Toast.makeText(this,"Bienvenido", Toast.LENGTH_SHORT).show()
        }
    }
}