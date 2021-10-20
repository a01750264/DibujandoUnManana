package mx.brg.dibujandounmanana

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.brg.dibujandounmanana.databinding.FragmentListaPropuestasBinding

class ListaPropuestas : Fragment() {

    companion object {
        fun newInstance() = ListaPropuestas()
    }

    private val viewModel : ListaPropuestasVM by viewModels()

    private lateinit var binding: FragmentListaPropuestasBinding

    private val adaptadorListaPropuestas = AdaptadorListaPropuestas(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaPropuestasBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvListaPropuestas.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaPropuestas
        }
    }

    private fun configurarEventos() {
        val preferencias = this.requireContext().getSharedPreferences("tokenAdmin",Context.MODE_PRIVATE)
        val token = preferencias.getString("token","none").toString()
        viewModel.descargarPropuestas(token)
    }

    private fun configurarObservadores() {
        viewModel.arrPropuestas.observe(viewLifecycleOwner) { lista ->
            adaptadorListaPropuestas.actualizar(lista)
        }
    }

}