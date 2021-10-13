package mx.brg.dibujandounmanana.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import mx.brg.dibujandounmanana.MainActivity
import mx.brg.dibujandounmanana.RegistarActivity
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.databinding.ActivityLoginBinding
import mx.brg.dibujandounmanana.model.DonanteLogIn
import mx.brg.dibujandounmanana.model.DonanteToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://189.232.85.142:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioDibujandoApi by lazy {
        retrofit.create(ServicioDibujandoApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        /*
        setContentView(R.layout.activity_login)

         */
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        binding.btnRegistrarse.setOnClickListener {
            println("registar")
            mostrarRegistarActivty()
        }

        binding.btnAcceder.setOnClickListener {
            val user = binding.etEmail.text.toString()
            val pswd = binding.etContrasena.text.toString()
            iniciarSesion(user, pswd)
        }




    }

    private fun mostrarRegistarActivty() {
        val registarActivity = Intent(this, RegistarActivity::class.java)
        startActivity(registarActivity)
    }

    private fun iniciarSesion(user: String, pswd: String) {
        val call = servicioDibujandoApi.logIn(DonanteLogIn(user, pswd))
        call.enqueue(object: Callback<DonanteToken> {
            override fun onResponse(call: Call<DonanteToken>, response: Response<DonanteToken>) {
                if(response.isSuccessful){
                    if(response.code() == 200){
                        println("token ${response.body()?.token}")
                    } else if(response.code() == 401){
                        println(response.body())
                    } else if (response.code() == 500){
                        println(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<DonanteToken>, t: Throwable) {
                println("Error: ${t.localizedMessage}")
            }
        })
        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
        this.finish()
    }
}