package mx.brg.dibujandounmanana

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.brg.dibujandounmanana.databinding.FragmentMisDonacionesBinding

/*
Este fragmento se encarga de mostrar la lista de donaciones que ha realizado un donante,
esto lo hace utilizando el token de la sesión del usuario en las preferencias
 */

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
        val preferencias = this.requireActivity().getSharedPreferences("tokenUsuario",Context.MODE_PRIVATE)
        val token = preferencias.getString("token","none")
        viewModel.descargarDatosMisDonaciones(token.toString())
    }

    private fun configurarObservadores() {
        viewModel.arrMisDonaciones.observe(viewLifecycleOwner) { lista ->
            adaptadorMisDonaciones.actualizar(lista)
        }
    }


}