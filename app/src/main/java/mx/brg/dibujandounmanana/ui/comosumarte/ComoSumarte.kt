package mx.brg.dibujandounmanana.ui.comosumarte

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.databinding.FragmentComoSumarteBinding

class ComoSumarte : Fragment() {

    private lateinit var binding: FragmentComoSumarteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComoSumarteBinding.inflate(layoutInflater)
        val vista = binding.root
        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegistrarIniciativaUsuario.setOnClickListener {
            val registrarIniciativaActivity = Intent(activity, RegistrarIniciativaActivity::class.java)
            startActivity(registrarIniciativaActivity)
        }
    }

}