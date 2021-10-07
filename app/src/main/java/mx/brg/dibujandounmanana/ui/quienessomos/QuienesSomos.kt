package mx.brg.dibujandounmanana.ui.quienessomos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.brg.dibujandounmanana.R

class QuienesSomos : Fragment() {

    companion object {
        fun newInstance() = QuienesSomos()
    }

    private lateinit var viewModel: QuienesSomosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quienes_somos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuienesSomosViewModel::class.java)
        // TODO: Use the ViewModel
        // Comentario para prueba
    }

}