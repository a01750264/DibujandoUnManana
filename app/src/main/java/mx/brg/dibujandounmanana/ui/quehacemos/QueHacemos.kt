package mx.brg.dibujandounmanana.ui.quehacemos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.brg.dibujandounmanana.R

/*
Fragmento que muestra la pantalla de ¿Qué hacemos?
 */

class QueHacemos : Fragment() {

    companion object {
        fun newInstance() = QueHacemos()
    }

    private lateinit var viewModel: QueHacemosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_que_hacemos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QueHacemosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}