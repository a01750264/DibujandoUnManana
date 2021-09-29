package mx.brg.dibujandounmanana.ui.comosumarte

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.brg.dibujandounmanana.R

class ComoSumarte : Fragment() {

    companion object {
        fun newInstance() = ComoSumarte()
    }

    private lateinit var viewModel: ComoSumarteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_como_sumarte, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComoSumarteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}