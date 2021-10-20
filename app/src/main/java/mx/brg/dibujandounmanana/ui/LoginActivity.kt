package mx.brg.dibujandounmanana.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import com.google.gson.Gson
import mx.brg.dibujandounmanana.MainActivity
import mx.brg.dibujandounmanana.RegistarActivity
import mx.brg.dibujandounmanana.admin.LoginAdminActivity
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
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val emailPatternMx = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"

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

        binding.btnAdmin.setOnClickListener {
            val loginAdminActivity = Intent(this, LoginAdminActivity::class.java)
            startActivity(loginAdminActivity)
        }

        binding.btnAcceder.setOnClickListener {
            val user = binding.etEmail.text.toString()
            val pswd = binding.etContrasena.text.toString()
            if (user.matches(emailPattern.toRegex()) == false && user.matches(emailPatternMx.toRegex()) == false ) {
                Toast.makeText(applicationContext, "Ingresa un correo electrónico válido",
                    Toast.LENGTH_SHORT).show()
            } else {
                if (pswd.length == 0) {
                    Toast.makeText(applicationContext, "Ingresa un contraseña",
                        Toast.LENGTH_SHORT).show()
                } else {
                    iniciarSesion(user, pswd)
                }
            }
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
                        getSharedPreferences("tokenUsuario", Context.MODE_PRIVATE).edit {
                            putString("token", response.body()?.token)
                            commit()
                        }
                        val mainActivity = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(mainActivity)
                        this@LoginActivity.finish()
                        println("token ${response.body()?.token}")
                    } else {
                        println("Error")
                    }
                }  else if(response.code() == 401){
                    Toast.makeText(applicationContext,"Revisa tus datos", Toast.LENGTH_SHORT).show()
                    println(response.body())
                } else if (response.code() == 500){
                    Toast.makeText(applicationContext, "Servidor caído", Toast.LENGTH_SHORT).show()
                    println(response.body())
                }
            }

            override fun onFailure(call: Call<DonanteToken>, t: Throwable) {
                println("Error: ${t.localizedMessage}")
            }
        })
    }
}