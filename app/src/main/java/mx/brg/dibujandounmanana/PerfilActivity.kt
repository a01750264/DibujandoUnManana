package mx.brg.dibujandounmanana

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.databinding.ActivityPerfilBinding
import mx.brg.dibujandounmanana.model.DonanteInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
Esta actividad muestra toda la información del usuario que inició sesión, se usa retrofit para
traer los datos del usuario y mostrarlos junto con sus donaciones utilizando un
FragmentContainerView
 */

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

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
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mostrarPerfil()
    }

    private fun mostrarPerfil() {
        val preferencias = this.getSharedPreferences("tokenUsuario",Context.MODE_PRIVATE)
        val token = preferencias.getString("token","none").toString()

        val call = servicioDibujandoApi.verInfoDonante("Bearer $token")
        call.enqueue(object: Callback<DonanteInfo> {
            override fun onResponse(call: Call<DonanteInfo>, response: Response<DonanteInfo>) {
                if (response.isSuccessful)
                {
                    if (response.code() == 200) {
                        println(response.body())
                        val nom = "${response.body()?.nombre} ${response.body()?.apellidoP} ${response.body()?.apellidoM}"
                        binding.tvDonanteNombre.text = nom
                        binding.tvDonanteFecha.text = response.body()?.fecha
                    } else {
                        println("Error: ${response.errorBody()}")
                    }
                } else {
                    if (response.code() == 403)
                    {
                        println("Usuario no ha iniciado sesión")
                    } else if (response.code() == 500){
                        println("Servidor caído")
                    }
                }
            }

            override fun onFailure(call: Call<DonanteInfo>, t: Throwable) {
                println("Error: ${t.localizedMessage}")
            }
        })
    }
}