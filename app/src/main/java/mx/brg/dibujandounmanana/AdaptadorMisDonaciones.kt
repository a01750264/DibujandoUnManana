package mx.brg.dibujandounmanana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.brg.dibujandounmanana.model.MiDonacion

class AdaptadorMisDonaciones(var arrMisDonaciones: ArrayList<MiDonacion>) :
    RecyclerView.Adapter<AdaptadorMisDonaciones.MiDonacionViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiDonacionViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_donacion, parent, false)
        return MiDonacionViewHolder(vista)
    }

    override fun onBindViewHolder(holder: MiDonacionViewHolder, position: Int) {
        holder.set(arrMisDonaciones[position])
        val vista = holder.itemView
        val layoutRenglon = vista.findViewById<LinearLayout>(R.id.layoutRenglon)
        layoutRenglon.setOnClickListener{
            println("Click sobre ${arrMisDonaciones[position]}")
        }
    }

    override fun getItemCount(): Int {
        return arrMisDonaciones.size
    }

    fun actualizar(lista: List<MiDonacion>?) {
        arrMisDonaciones.clear()
        if (lista != null) {
            arrMisDonaciones.addAll(lista)
        }
        notifyDataSetChanged()
    }

    class MiDonacionViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        private val tvDonacionTitulo = vista.findViewById<TextView>(R.id.tvDonacionTitulo)
        private val tvDonacionCantidad = vista.findViewById<TextView>(R.id.tvDonacionCantidad)
        private val tvDonacionFecha = vista.findViewById<TextView>(R.id.tvDonacionFecha)

        fun set(miDonacion: MiDonacion) {
            tvDonacionTitulo.text = miDonacion.titulo
            tvDonacionCantidad.text = miDonacion.cantidad.toString()
            tvDonacionFecha.text = miDonacion.fecha
        }
    }

}
