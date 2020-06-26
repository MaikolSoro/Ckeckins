package com.example.ckeckins.RecyclerViewPrincipal

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ckeckins.R
import com.example.ckeckins.Venue
import java.text.FieldPosition

class AdaptadorCustom(item: ArrayList<Venue>, var listener: ClickListener, var longClickListener: LongClickListener) {

    var items: ArrayList<Venue>? = null
    var multiSeleccion = false

    var itemsSeleccionados: ArrayList<Int>? = null
    var viewHolder: RecyclerView.ViewHolder? = null

    init {
        this.items = items
        itemsSeleccionados = ArrayList()
    }

    /*
    override  fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.template__venues, parent, false)
        viewHolder = ViewHolder(vista, listener, longClickListener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.nombre?.text = item?.name
        if (itemsSeleccionados?.contains(position)!!) {
            holder.vista.setBackgroundColor(Color.LTGRAY)

        } else {
            holder.vista.setBackgroundColor(Color.WHITE)
        }
    }
  */
    fun iniciarActionMode() {
        multiSeleccion = true
    }

    fun destruirActionMode() {
        multiSeleccion = false
        itemsSeleccionados?.clear()
        //notifyDataSetChanged()
    }
    fun terminarActionMode() {
        for (item in itemsSeleccionados!!){
            itemsSeleccionados?.remove(item)
        }
        multiSeleccion = false
      //  notifyDataSetChanged()
    }

    fun seleccionarItem(index:Int) {
        if(multiSeleccion) {
            if(itemsSeleccionados?.contains(index)!!) {
                itemsSeleccionados?.remove(index)
            } else {
                itemsSeleccionados?.add(index)
            }
          //  notifyDataSetChanged()
        }
    }
    /*
    fun obtenerNumeroElementosSeleccionados(): Int {
      //  return itemsSeleccionados?.count()
    }
    */


    fun eliminarSeleccionados() {
        if(itemsSeleccionados?.count()!! > 0) {
            var itemsEliminados = ArrayList<Venue>()

            for(index in itemsEliminados!!) {
              //  itemsEliminados.add(items?.get(index)!!)
            }
            items?.removeAll(itemsEliminados)
            itemsSeleccionados?.clear()
        }
    }
    class ViewHolder(vista: View, Listener: ClickListener, longClickListener: LongClickListener) {

        var vista = vista;
        var nombre: TextView? = null
        var listener: ClickListener? = null
        var longListener: LongClickListener? = null

        init {
            nombre = vista.findViewById(R.id.tvNombre)

            this.listener = listener
            this.longListener = longClickListener

            //vista.setOnClickListener(this)
            //vista.setOnLongClickListener(this)
        }

        /*
           override fun OnClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.onClick(v!!, adapterPosition)
            return true
        }
         */
    }

}