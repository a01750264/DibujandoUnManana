package mx.brg.dibujandounmanana.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.databinding.ActivityEditarCampaniasBinding
import mx.brg.dibujandounmanana.databinding.ActivityEditarIniciativasBinding

class EditarCampaniasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarCampaniasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditarCampaniasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabCrearCampania.setOnClickListener {
            val activityCrearCampanias = Intent(this, CrearCampaniasActivity::class.java)
            startActivity(activityCrearCampanias)
        }
    }
}