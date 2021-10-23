package mx.brg.dibujandounmanana.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.brg.dibujandounmanana.ui.comosumarte.ListaIniciativas
import mx.brg.dibujandounmanana.databinding.FragmentListaIniciativasAdminBinding

/*
Este fragmento se encarga de mostrar al administrador las Iniciativas dadas de alta en la base
de datos utilizando el adaptador para tener la información de cada una de éstas
 */

class ListaIniciativasAdmin : Fragment(), RenglonListenerIniciativaAdmin {

    companion object {
        fun newInstance() = ListaIniciativas()
    }

    private val viewModel: ListaIniciativasAdminVM by viewModels()

    private lateinit var binding: FragmentListaIniciativasAdminBinding

    private val adaptadorListaIniciativasAdmin = AdaptadorListaIniciativasAdmin(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaIniciativasAdminBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvListaIniciativasAdmin.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaIniciativasAdmin
        }
    }

    private fun configurarObservadores() {
        viewModel.arrIniciativas.observe(viewLifecycleOwner) { lista ->
            adaptadorListaIniciativasAdmin.actualizar(lista)
        }
    }

    private fun configurarEventos() {
        viewModel.descargarIniciativas()
        adaptadorListaIniciativasAdmin.listener = this
    }

    override fun clickEnRenglon(position: Int)
    {
        val campania = adaptadorListaIniciativasAdmin.arrIniciativas[position]
        println("Cambiar a pantalla ${campania.nombre}")
    }

}