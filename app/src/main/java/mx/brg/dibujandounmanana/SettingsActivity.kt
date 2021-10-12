package mx.brg.dibujandounmanana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.brg.dibujandounmanana.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            guardarCambios()
        }
    }

    private fun guardarCambios() {
        val mainActivity= Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
    }
}