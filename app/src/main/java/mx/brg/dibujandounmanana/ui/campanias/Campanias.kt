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
import mx.brg.dibujandounmanana.databinding.FragmentCampaniasBinding

class Campanias : Fragment() {

    private lateinit var binding: FragmentCampaniasBinding

    companion object {
        fun newInstance() = Campanias()
    }

    private lateinit var viewModel: CampaniasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCampaniasBinding.inflate(layoutInflater)
        val vista = binding.root
        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        binding.btnVerMas2.setOnClickListener{
            val urlIntent = Intent(Intent.ACTION_VIEW)
            urlIntent.data = Uri.parse("https://www.dibujando.org.mx/personas/")
            startActivity(urlIntent)
        }

        binding.btnVerMasJuntos.setOnClickListener{
            val urlIntent = Intent(Intent.ACTION_VIEW)
            urlIntent.data = Uri.parse("https://www.dibujando.org.mx/")
            startActivity(urlIntent)
        }
    }

}