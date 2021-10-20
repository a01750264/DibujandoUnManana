package mx.brg.dibujandounmanana

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.databinding.ActivityMainBinding
import mx.brg.dibujandounmanana.databinding.ActivityRegistarBinding
import mx.brg.dibujandounmanana.model.DonanteSignUp
import mx.brg.dibujandounmanana.ui.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RegistarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistarBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val emailPatternMx = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.64:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioDibujandoApi by lazy {
        retrofit.create(ServicioDibujandoApi::class.java)
    }

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

            val nombre = binding.etNombreRegistrar.text.toString()
            val apellidoP = binding.etApellidoPaternoRegistrar.text.toString()
            val apellidoM = binding.etApellidoMaternoRegistrar.text.toString()
            val email = binding.etEmailRegistrar.text.toString()
            val pass = binding.etContrasenaRegistar.text.toString()
            val fecha = binding.tvFechaNac.text.toString()

            val donanteSignUp = DonanteSignUp(nombre,apellidoP,apellidoM,email,pass,fecha)
            val call = servicioDibujandoApi.signUp(donanteSignUp)
            call.enqueue(object: Callback<Map<String,String>> {
                override fun onResponse(
                    call: Call<Map<String, String>>,
                    response: Response<Map<String, String>>
                ) {
                    if (response.isSuccessful)
                    {
                        Toast.makeText(this@RegistarActivity, "Registro Exitoso", Toast.LENGTH_SHORT).show()
                        val loginActivity = Intent(this@RegistarActivity, LoginActivity::class.java)
                        this@RegistarActivity
                        startActivity(loginActivity)
                    } else {
                        println(response.body())
                    }
                }

                override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                    println("${t.localizedMessage}")
                }

            })
        }

        binding.btnFechaNac.setOnClickListener {
            val datePicker = DatePickerDialog(this, datePicker, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),
            calendario.get(Calendar.DAY_OF_MONTH))
            datePicker.datePicker.maxDate = System.currentTimeMillis()
            datePicker.show()
        }

    }

    private fun formatInput(calendario: Calendar): String {
        val formato = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(formato, Locale.JAPAN)
        println(sdf.format(calendario.time).toString())
        return sdf.format(calendario.time).toString()
    }


    private fun verificarFechaNac(): Boolean {
        var fechaValido = true
        if (binding.tvFechaNac.text == "yyyy-MM-dd") {
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