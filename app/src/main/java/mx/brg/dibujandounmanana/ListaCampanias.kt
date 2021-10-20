package mx.brg.dibujandounmanana

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.brg.dibujandounmanana.databinding.FragmentListaCampaniasBinding

class ListaCampanias : Fragment(), RenglonListenerCampanias {

    companion object {
        fun newInstance() = ListaCampanias()
    }

    private val viewModel: ListaCampaniasVM by viewModels()

    private lateinit var binding: FragmentListaCampaniasBinding

    private val adaptadorListaCampanias = AdaptadorListaCampanias(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaCampaniasBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvListaCampanias.apply {
            layoutManager = LinearLayoutManager(context)
            adapter =adaptadorListaCampanias
        }
    }

    private fun configurarObservadores() {
        viewModel.arrCampanias.observe(viewLifecycleOwner) { lista ->
            adaptadorListaCampanias.actualizar(lista)
        }
    }

    private fun configurarEventos() {
        viewModel.descargarCampanias()
        adaptadorListaCampanias.listener = this
    }

    override fun clickEnRenglon(position: Int)
    {
        val campania = adaptadorListaCampanias.arrCampanias[position]
        println("Cambiar a pantalla ${campania.nombre}")
    }

}