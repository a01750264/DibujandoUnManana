package mx.brg.dibujandounmanana.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.databinding.ActivityEditarIniciativasBinding
import mx.brg.dibujandounmanana.databinding.ActivityMainAdminBinding

/*
Esta actividad permite al administrador crear nuevas Iniciativas y ver las existentes
 */

class EditarIniciativasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarIniciativasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditarIniciativasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabCrearIniciativa.setOnClickListener {
            val crearIniciativasActivity = Intent(this, CrearIniciativasActivity::class.java)
            startActivity(crearIniciativasActivity)
        }
    }
}