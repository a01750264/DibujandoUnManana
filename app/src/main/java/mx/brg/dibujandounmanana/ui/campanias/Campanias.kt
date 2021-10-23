package mx.brg.dibujandounmanana.ui.campanias

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.brg.dibujandounmanana.R

/*
Fragmento que carga la pantalla de las campanias
 */

class Campanias : Fragment() {

    companion object {
        fun newInstance() = Campanias()
    }

    private lateinit var viewModel: CampaniasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_campanias, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CampaniasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}