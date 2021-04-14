package com.example.tutorly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val subjects: ArrayList<String>, val listener: (Int) -> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var holder: ViewHolder

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView
        val textImage: ImageView
        val parentLayout: RelativeLayout


        init {
            textView = view.findViewById(R.id.subject_name)
            textImage = view.findViewById(R.id.text_image)
            parentLayout = view.findViewById(R.id.parent_layout)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_layout, parent, false)
        holder = ViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { listener(position) }
        holder.textView.text = subjects[position];
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    fun deleteItem(index: Int): String {
        val subject = subjects[index]

        subjects.removeAt(index)
        notifyDataSetChanged()

        return subject
    }

    fun insertItem(subject: String)
    {
        subjects.add(subject);
        notifyDataSetChanged()
    }

}