package com.example.appventafarmacia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_medicina.view.*

class medicinaAdap (private val mContext: Context, private val listamedicinas: List<medicina>) : ArrayAdapter<medicina>(mContext, 0,listamedicinas) {
    override fun getView(position:Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_medicina, parent, false)

        val medicina= listamedicinas[position]

        layout.nombre.text= medicina.nombre
        layout.precio.text= medicina.precio.toString()
        layout.imageView.setImageResource(medicina.imagen)
        return layout

    }
}