package mx.brg.dibujandounmanana.ui.campanias

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.brg.dibujandounmanana.databinding.FragmentListaCampaniasBinding

/*
Este fragmento se encarga de mostrar las Campanias dadas de alta en la base de datos utilizando
el adaptador para tener la información de cada una de éstas así como escuchar cuando si haga
click sobre el botón de Donar o Ver Más
 */

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

    override fun clickEnVerMas(position: Int)
    {
        val campania = adaptadorListaCampanias.arrCampanias[position]
        println("Cambiar a pantalla ${campania.nombre}")
        this.requireContext().getSharedPreferences("campaniaSeleccionada", Context.MODE_PRIVATE).edit {
            putInt("id",campania.id)
            commit()
        }
    }

    override fun clickEnDonar(position: Int) {
        val campania = adaptadorListaCampanias.arrCampanias[position]
        println("Cambiar a Donar con referencia ${campania.nombre}")
        this.requireContext().getSharedPreferences("campaniaSeleccionada", Context.MODE_PRIVATE).edit {
            putInt("id",campania.id)
            commit()
        }
    }

}