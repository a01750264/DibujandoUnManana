package mx.brg.dibujandounmanana.ui.contactanos

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.databinding.FragmentContactanosBinding

class Contactanos : Fragment() {

    private lateinit var binding: FragmentContactanosBinding

    companion object {
        fun newInstance() = Contactanos()
    }

    private lateinit var viewModel: ContactanosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactanosBinding.inflate(layoutInflater)
        val vista = binding.root
        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnMailMx.setOnClickListener {
            val mailIntent = Intent(Intent.ACTION_SEND)
            mailIntent.data = Uri.parse("mailto:")
            mailIntent.type = "text/plain"
            mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("contacto@dibujando.org.mx"))
            startActivity(mailIntent)
        }

        binding.btnTelMx.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + "+525521225286")
            startActivity(dialIntent)
        }
        binding.btnMailMty.setOnClickListener {
            val mailIntent = Intent(Intent.ACTION_SEND)
            mailIntent.data = Uri.parse("mailto:")
            mailIntent.type = "text/plain"
            mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("gdi.mty@dibujando.org.mx"))
            startActivity(mailIntent)
        }
        binding.btnTelMty.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + "+528144448643")
            startActivity(dialIntent)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContactanosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}