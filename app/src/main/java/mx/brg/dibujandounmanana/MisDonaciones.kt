package mx.brg.dibujandounmanana

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.brg.dibujandounmanana.databinding.FragmentMisDonacionesBinding

class MisDonaciones : Fragment() {

    companion object {
        fun newInstance() = MisDonaciones()
    }

    private val viewModel: MisDonacionesVM by viewModels()

    private lateinit var binding: FragmentMisDonacionesBinding

    private val adaptadorMisDonaciones = AdaptadorMisDonaciones(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMisDonacionesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarObservadores()
        configurarEventos()
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvMisDonaciones.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorMisDonaciones
        }
    }

    private fun configurarEventos() {
        viewModel.leerDatos()
    }

    private fun configurarObservadores() {
        viewModel.arrMisDonaciones.observe(viewLifecycleOwner) { lista ->
            adaptadorMisDonaciones.actualizar(lista)
        }
    }


}