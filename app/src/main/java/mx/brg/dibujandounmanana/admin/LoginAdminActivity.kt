package mx.brg.dibujandounmanana.admin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.databinding.ActivityLoginAdminBinding
import mx.brg.dibujandounmanana.databinding.ActivityLoginBinding
import mx.brg.dibujandounmanana.model.AdminLogIn
import mx.brg.dibujandounmanana.model.AdminToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
Esta actividad permite que el administrador inicie sesión en la aplicación, se verifica
que los datos sean correctos a través del servidor utilizando retrofit
 */

class LoginAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginAdminBinding
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

        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnAccederAdmin.setOnClickListener {

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

    private fun iniciarSesion(user: String, pswd: String) {
        val call = servicioDibujandoApi.logInAdmin(AdminLogIn(user, pswd))
        call.enqueue(object: Callback<AdminToken> {
            override fun onResponse(call: Call<AdminToken>, response: Response<AdminToken>) {
                if (response.isSuccessful){
                    if (response.code() == 200)
                    {
                        getSharedPreferences("tokenAdmin", Context.MODE_PRIVATE).edit {
                            putString("token", response.body()?.token)
                            commit()
                        }
                        val mainAdminActivity = Intent(this@LoginAdminActivity, MainAdminActivity::class.java)
                        startActivity(mainAdminActivity)
                        this@LoginAdminActivity.finish()
                        println("token ${response.body()?.token}")
                    } else {
                        println("Error")
                    }
                } else if (response.code() == 401){
                    Toast.makeText(applicationContext,"Revisa tus datos", Toast.LENGTH_SHORT).show()
                    println(response.body())
                } else if (response.code() == 500){
                    Toast.makeText(applicationContext, "Servidor caído", Toast.LENGTH_SHORT).show()
                    println(response.body())
                }
            }
            override fun onFailure(call: Call<AdminToken>, t: Throwable) {
                println("Error: ${t.localizedMessage}")
            }
        })
    }
}