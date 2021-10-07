package mx.brg.dibujandounmanana.ui.donar

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.databinding.ActivityDonarBinding


class Donar : AppCompatActivity() {

    private lateinit var binding: ActivityDonarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDonarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        }
    }