package mx.brg.dibujandounmanana.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_login.*
import mx.brg.dibujandounmanana.MainActivity
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.databinding.ActivityLoginBinding
import mx.brg.dibujandounmanana.databinding.FragmentContactanosBinding

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


        binding.btnAcceder.setOnClickListener {
            mostrarMainActivity()
        }

    }

    private fun mostrarMainActivity() {

        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
        this.finish()
    }
}