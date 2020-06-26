package com.example.ckeckins.RecyclerViewPrincipal

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ckeckins.R
import com.example.ckeckins.Venue

class AdaptadorCustom(item: ArrayList<Venue>, var listener: ClickListener, var longClickListener: LongClickListener) {

    var items: ArrayList<Venue>? = null
    var multiSeleccion = false

    var itemsSeleccionados: ArrayList<Int>? = null
    var viewHolder: ViewHolder? = null

    init {
        this.items = items
        itemsSeleccionados = ArrayList()
    }


    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.template__venues, parent, false)
            viewHolder = ViewHolder(vista, listener, longClickListener)
            return viewHolder!!
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
        override fun getItemCount(): Int {
            return items?.count()!!
        }
     */

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.nombre?.text = item?.name
        if (itemsSeleccionados?.contains(position)!!) {
            holder.vista.setBackgroundColor(Color.LTGRAY)

        } else {
            holder.vista.setBackgroundColor(Color.WHITE)
        }
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
            //notifyDataSetChanged()
        }
    }

   // fun obtenerNumeroElementosSeleccionados(): Int {
        //return itemsSeleccionados?.count()
    //}



    fun eliminarSeleccionados() {
        if(itemsSeleccionados?.count()!! > 0) {
            var itemsEliminados = ArrayList<Venue?>()

            for(index in itemsEliminados!!) {
               // itemsEliminados.add(items?.get(index)!!)
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

            ///vista.setOnClickListener(this)
            // vista.setOnLongClickListener(this)s
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