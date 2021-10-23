package mx.brg.dibujandounmanana.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.model.PropuestaBD

/*
Este adaptador se encarga de dar valor al recycler view que contiene las Propuestas dadas de alta
en la base de datos para mostrarlas al administrador
 */

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