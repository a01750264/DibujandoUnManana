package mx.brg.dibujandounmanana.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.brg.dibujandounmanana.MainActivity
import mx.brg.dibujandounmanana.RegistarActivity
import mx.brg.dibujandounmanana.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

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
            mostrarMainActivity()
        }




    }

    private fun mostrarRegistarActivty() {
        val registarActivity = Intent(this, RegistarActivity::class.java)
        startActivity(registarActivity)
    }

    private fun mostrarMainActivity() {

        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
        this.finish()
    }
}