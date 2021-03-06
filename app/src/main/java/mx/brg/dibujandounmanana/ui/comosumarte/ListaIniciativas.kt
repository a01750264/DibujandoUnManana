package mx.brg.dibujandounmanana.ui.comosumarte

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.brg.dibujandounmanana.databinding.FragmentListaIniciativasBinding

/*
Este fragmento se encarga de mostrar las Iniciativas dadas de alta en la base de datos utilizando
el adaptador para tener la información de cada una de éstas así como escuchar cuando si haga
click sobre el botón de Ver Más
 */

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
        val iniciativa = adaptadorListainiciativas.arrIniciativas[position]
        println("Cambiar a pantalla ${iniciativa.nombre}")
        this.requireContext().getSharedPreferences("iniciativaSeleccionada", Context.MODE_PRIVATE).edit {
            putInt("id",iniciativa.id)
            commit()
        }

    }
}