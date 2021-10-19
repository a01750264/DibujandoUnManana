package mx.brg.dibujandounmanana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.databinding.ActivityMainBinding
import mx.brg.dibujandounmanana.databinding.ActivityRegistarBinding
import java.text.ParseException
import java.text.SimpleDateFormat

class RegistarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistarBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val emailPatternMx = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registar)

        supportActionBar?.hide()

        binding = ActivityRegistarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistarCuenta.setOnClickListener {
            val camposCompletos = verificarCamposCompletos()
            val correoValido = verificarCorreo()
            val pswdValido = verificarPassword()
            //val fechaValido = verificarFechaNac()
        }

    }

    /*
    private fun verificarFechaNac(): Boolean {
        var fechaValido = true
        val format = SimpleDateFormat("dd-MM-yyyy")
        val date = binding.etFechaNacimiento.text.toString()
        format.setLenient(false)

        try {
            format.parse(date)
        } catch (e: ParseException) { fechaValido = false }

        return fechaValido
    }

     */

    private fun verificarPassword(): Boolean {
        var pswdValido = true
        if (binding.etContrasenaRegistar.text?.length!! < 8) {
            Toast.makeText(applicationContext, "Ingresa una contraseña de 8 caracteres o más",
                Toast.LENGTH_SHORT).show()
            pswdValido = false
        }
        return pswdValido
    }

    private fun verificarCorreo(): Boolean {
        var correoValido = true
        if (binding.etEmailRegistrar.text?.matches(emailPattern.toRegex()) == false &&
            binding.etEmailRegistrar.text?.matches(emailPatternMx.toRegex()) == false) {
            Toast.makeText(applicationContext, "Ingresa un correo electrónico válido",
                Toast.LENGTH_SHORT).show()
            correoValido = false
        }
        return correoValido
    }

    private fun verificarCamposCompletos(): Boolean {
        val listaEt = listOf<EditText>(binding.etEmailRegistrar, binding.etContrasenaRegistar,
            binding.etNombreRegistrar, binding.etApellidoPaternoRegistrar,
            binding.etApellidoMaternoRegistrar, binding.etFechaNacimiento)
        var camposCompletos = true
        for (et in listaEt) {
            if (et.text.isEmpty()) {
                Toast.makeText(applicationContext, "Llena el campo ${et.hint}",
                    Toast.LENGTH_SHORT).show()
                camposCompletos = false
                break
            }
        }
        return camposCompletos
    }
}