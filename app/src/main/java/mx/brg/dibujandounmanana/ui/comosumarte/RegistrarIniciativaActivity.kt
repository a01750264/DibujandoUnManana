package mx.brg.dibujandounmanana.ui.comosumarte

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.databinding.ActivityRegistrarIniciativaBinding
import mx.brg.dibujandounmanana.model.Propuesta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
Actividad que permite a un donante registrar una propuesta de iniciativa por medio de retrofit
 */

class RegistrarIniciativaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarIniciativaBinding

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
        binding = ActivityRegistrarIniciativaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarObservadores()
    }

    private fun configurarObservadores() {
        binding.btnProponer.setOnClickListener {
            val etNombreIniciativa = binding.etNombreInciativaUsuario.text.toString()
            val etDescripcionIniciativa = binding.etDescripcionInciativaUsuario.text.toString()
            val propuesta = Propuesta(etNombreIniciativa, etDescripcionIniciativa)
            val preferencias = getSharedPreferences("tokenUsuario", Context.MODE_PRIVATE)
            val token = preferencias.getString("token","none")

            val call = servicioDibujandoApi.proponerIniciativa("Bearer $token", propuesta)
            call.enqueue(object: Callback<Map<String,String>> {
                override fun onResponse(
                    call: Call<Map<String, String>>,
                    response: Response<Map<String, String>>
                ) {
                    if (response.isSuccessful)
                    {
                        if (response.code() == 200)
                        {
                            Toast.makeText(this@RegistrarIniciativaActivity, "Gracias revisaremos tu propuesta", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        println("Error: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                    println("Error: ${t.localizedMessage}")
                }
            })
        }
    }
}