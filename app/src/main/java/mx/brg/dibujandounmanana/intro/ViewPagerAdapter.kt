package mx.brg.dibujandounmanana.intro

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/*
    En esta Clase se regresa la posición de lista de los fragmentos de la Intro de la app.
 */
class ViewPagerAdapter(
    val items: ArrayList<Fragment>,
    activity: AppCompatActivity
    ): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}