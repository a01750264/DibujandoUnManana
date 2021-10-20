package mx.brg.dibujandounmanana

import android.app.ProgressDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.brg.dibujandounmanana.model.IniciativaBD
import mx.brg.dibujandounmanana.model.PropuestaBD

class AdaptadorListaPropuestas(var arrPropuestas: ArrayList<PropuestaBD>) :
    RecyclerView.Adapter<AdaptadorListaPropuestas.ListaPropuestasViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaPropuestasViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_propuesta, parent, false)
        return ListaPropuestasViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ListaPropuestasViewHolder, position: Int) {
        holder.set(arrPropuestas[position])
    }

    override fun getItemCount(): Int {
        return arrPropuestas.size
    }

    fun actualizar(lista: List<PropuestaBD>?)
    {
        arrPropuestas.clear()
        if (lista != null)
        {
            arrPropuestas.addAll(lista)
        }
        notifyDataSetChanged()
    }

    class ListaPropuestasViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        private val tvTituloPropuesta = vista.findViewById<TextView>(R.id.tvTituloPropuesta)
        private val tvDescripcionPropuesta = vista.findViewById<TextView>(R.id.tvDescripcionPropuesta)

        fun set(propuesta: PropuestaBD)
        {
            tvTituloPropuesta.setText(propuesta.titulo)
            tvDescripcionPropuesta.setText(propuesta.descripcion)
        }
    }

}