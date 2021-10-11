package mx.brg.dibujandounmanana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.databinding.ActivityMainBinding
import mx.brg.dibujandounmanana.databinding.ActivityRegistarBinding

class RegistarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registar)

        supportActionBar?.hide()

        binding = ActivityRegistarBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}