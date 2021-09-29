package mx.brg.dibujandounmanana.ui.contactanos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.brg.dibujandounmanana.R

class Contactanos : Fragment() {

    companion object {
        fun newInstance() = Contactanos()
    }

    private lateinit var viewModel: ContactanosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contactanos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContactanosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}