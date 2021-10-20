package mx.brg.dibujandounmanana.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.VerPropuestasActivity
import mx.brg.dibujandounmanana.databinding.ActivityEditarCampaniasBinding
import mx.brg.dibujandounmanana.databinding.ActivityEditarIniciativasBinding
import mx.brg.dibujandounmanana.databinding.ActivityMainAdminBinding

class MainAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEditarCampanias.setOnClickListener {
            val editCampAdminActivity = Intent(this, EditarCampaniasActivity::class.java)
            startActivity(editCampAdminActivity)
        }

        binding.btnEditarIniciativas.setOnClickListener {
            val editIniAdminActivity = Intent(this, EditarIniciativasActivity::class.java)
            startActivity(editIniAdminActivity)
        }

        binding.btnVerPropuestas.setOnClickListener {
            val verPropuestasActivity = Intent(this, VerPropuestasActivity::class.java)
            startActivity(verPropuestasActivity)
        }
    }
}