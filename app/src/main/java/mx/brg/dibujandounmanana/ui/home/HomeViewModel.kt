package mx.brg.dibujandounmanana.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Pantalla de Inicio"
    }
    val text: LiveData<String> = _text
}