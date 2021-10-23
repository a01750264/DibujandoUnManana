package mx.brg.dibujandounmanana

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.brg.dibujandounmanana.databinding.FragmentListaCampaniasAdminBinding

/*
Este fragmento se encarga de mostrar al administrador las Campanias dadas de alta en la base
de datos utilizando el adaptador para tener la información de cada una de éstas
 */

class ListaCampaniasAdmin : Fragment(), RenglonListenerCampaniasAdmin {

    companion object {
        fun newInstance() = ListaCampaniasAdmin()
    }

    private val viewModel: ListaCampaniasAdminVM by viewModels()

    private lateinit var binding: FragmentListaCampaniasAdminBinding

    private val adaptadorListaCampaniasAdmin = AdaptadorListaCampaniasAdmin(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaCampaniasAdminBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvListaCampaniasAdmin.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaCampaniasAdmin
        }
    }

    private fun configurarObservadores() {
        viewModel.arrCampanias.observe(viewLifecycleOwner) { lista ->
            adaptadorListaCampaniasAdmin.actualizar(lista)
        }
    }

    private fun configurarEventos() {
        viewModel.descargarCampanias()
        adaptadorListaCampaniasAdmin.listener = this
    }

    override fun clickEnRenglon(position: Int)
    {
        val campania = adaptadorListaCampaniasAdmin.arrCampanias[position]
        println("Cambiar a pantalla ${campania.nombre}")
    }

}