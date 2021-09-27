package mx.brg.dibujandounmanana.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Pantalla ¿Que hacemos?"
    }
    val text: LiveData<String> = _text
}