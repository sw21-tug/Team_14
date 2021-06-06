package com.example.tutorly

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class RecyclerViewAdapter(private val context: Context, private val subjects: ArrayList<Subject>/*, val listener: (Int) -> Unit*/) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var holder: ViewHolder

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView
        val subjectNameView: TextView
        val subjectDescView: TextView
        val subjectImage: ImageView

        init {
            subjectNameView = view.findViewById(R.id.filterActivity_subject_name)
            subjectDescView = view.findViewById(R.id.filterActivity_subject_description)
            subjectImage = view.findViewById(R.id.filterActivity_subject_image)
            cardView = view.findViewById(R.id.filterActivity_card_view)

        }
    }

    fun updateSubjects(newSubjects: ArrayList<Subject>) {
        subjects.clear()
        subjects.addAll(newSubjects)
        notifyDataSetChanged()
    }

    fun getSubjects() : ArrayList<Subject>{

        return subjects
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filter_cardview_layout, parent, false)
        holder = ViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.subjectNameView.text = subjects[position].subjectName
        holder.subjectDescView.text = subjects[position].subjectDesc
        holder.subjectImage.setImageResource(R.drawable.ic_launcher_foreground)
        holder.cardView.setOnClickListener {view: View ->
            Log.println(Log.INFO, "RecyclerviewAdapter", "You clicked: " + holder.adapterPosition)
            holder.cardView.toggle()
            subjects[holder.adapterPosition].isSelected = holder.cardView.isChecked
            if(holder.cardView.isChecked){
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.checkedCard))
            }
            else{
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.uncheckedCard))
            }
            Log.println(Log.INFO, "RecyclerViewAdapter", "Position: " + holder.adapterPosition + " Is selected: " + subjects[holder.adapterPosition].isSelected)
        }
    }

    fun getSelectedSubjects() : ArrayList<Subject> {

        var selectedSubjects = ArrayList<Subject>()

        for(subject in subjects){

            if(subject.isSelected) {
                selectedSubjects.add(subject)
            }
        }
        return selectedSubjects
    }

    override fun getItemCount(): Int {
        return subjects.size
    }
}