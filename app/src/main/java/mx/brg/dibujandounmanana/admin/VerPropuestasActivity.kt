package mx.brg.dibujandounmanana.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.brg.dibujandounmanana.R

/*
Esta actividad muestra la lista de propuestas de los donantes
 */

class VerPropuestasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_propuestas)
    }
}