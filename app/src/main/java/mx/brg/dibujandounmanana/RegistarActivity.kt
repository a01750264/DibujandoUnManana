package mx.brg.dibujandounmanana

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.databinding.ActivityMainBinding
import mx.brg.dibujandounmanana.databinding.ActivityRegistarBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RegistarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistarBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val emailPatternMx = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registar)

        supportActionBar?.hide()

        val calendario = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            calendario.set(Calendar.YEAR, year)
            calendario.set(Calendar.MONTH, month)
            calendario.set(Calendar.DAY_OF_MONTH, day)
            val fechaNacStr = formatInput(calendario)
            binding.tvFechaNac.text = fechaNacStr

        }
        binding = ActivityRegistarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistarCuenta.setOnClickListener {
            val camposCompletos = verificarCamposCompletos()
            val correoValido = verificarCorreo()
            val pswdValido = verificarPassword()
            val fechaValido = verificarFechaNac()
        }

        binding.btnFechaNac.setOnClickListener {
            val datePicker = DatePickerDialog(this, datePicker, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),
            calendario.get(Calendar.DAY_OF_MONTH))
            datePicker.datePicker.maxDate = System.currentTimeMillis()
            datePicker.show()
        }

    }

    private fun formatInput(calendario: Calendar): String {
        val formato = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(formato, Locale.CANADA)
        println(sdf.format(calendario.time).toString())
        return sdf.format(calendario.time).toString()
    }


    private fun verificarFechaNac(): Boolean {
        var fechaValido = true
        if (binding.tvFechaNac.text == "dd-MM-yyyy") {
            Toast.makeText(applicationContext, "Ingresa una fecha de nacimiento",
                Toast.LENGTH_SHORT).show()
            fechaValido = false
        }
        return fechaValido
    }

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
            binding.etApellidoMaternoRegistrar)
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