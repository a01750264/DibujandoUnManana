package mx.brg.dibujandounmanana

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.brg.dibujandounmanana.databinding.FragmentListaIniciativasBinding

class ListaIniciativas : Fragment(), RenglonListenerIniciativa {

    companion object {
        fun newInstance() = ListaIniciativas()
    }

    private val viewModel: ListaIniciativasVM by viewModels()

    private lateinit var binding: FragmentListaIniciativasBinding

    private val adaptadorListainiciativas = AdaptadorListaIniciativas(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaIniciativasBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvListaIniciativas.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListainiciativas
        }
    }

    private fun configurarObservadores() {
        viewModel.arrIniciativas.observe(viewLifecycleOwner) { lista ->
            adaptadorListainiciativas.actualizar(lista)
        }
    }

    private fun configurarEventos() {
        viewModel.descargarIniciativas()
        adaptadorListainiciativas.listener = this
    }

    override fun clickEnRenglon(position: Int)
    {
        val campania = adaptadorListainiciativas.arrIniciativas[position]
        println("Cambiar a pantalla ${campania.nombre}")
    }
}