package mx.brg.dibujandounmanana.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.intro.ViewPagerAdapter

/*
    En esta actividad se utiliza un ViewPager2 para juntar fragmentos de Intro de la app.
 */

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        supportActionBar?.hide()

        val viewPager: ViewPager2 = findViewById(R.id.vpIntro)

        // Se crea una lista de los fragmentos de la Intro de la app.
        val fragments: ArrayList<Fragment> = arrayListOf(
            IntroPage1Fragment(),
            IntroPage2(),
            IntroPage3()
        )

        val adapter = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapter
        
    }
}