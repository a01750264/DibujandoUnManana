package mx.brg.dibujandounmanana.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import mx.brg.dibujandounmanana.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val viewPager: ViewPager2 = findViewById(R.id.vpIntro)
    }
}