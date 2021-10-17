package mx.brg.dibujandounmanana.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.brg.dibujandounmanana.databinding.ActivityLoginAdminBinding
import mx.brg.dibujandounmanana.databinding.ActivityLoginBinding

class LoginAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnAccederAdmin.setOnClickListener {
            val mainAdminActivity = Intent(this, MainAdminActivity::class.java)
            startActivity(mainAdminActivity)
        }


    }
}