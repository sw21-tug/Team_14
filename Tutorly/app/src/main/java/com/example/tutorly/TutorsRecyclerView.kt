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
import com.example.tutorly.database.Tutor
import com.google.android.material.card.MaterialCardView

class TutorsRecyclerView(private val context: Context, private val tutors: ArrayList<Tutor>) : RecyclerView.Adapter<TutorsRecyclerView.ViewHolder>() {

    lateinit var holder: ViewHolder

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView
        val tutorNameView: TextView
        val tutorImage: ImageView

        init {
            tutorNameView = view.findViewById(R.id.tutors_name)
            tutorImage = view.findViewById(R.id.tutors_image)
            cardView = view.findViewById(R.id.tutors_card_view)

        }
    }

    fun getTutors() : ArrayList<Tutor>{

        return tutors
    }

    fun updateTutors(newTutors: ArrayList<Tutor>) {
        tutors.clear()
        tutors.addAll(newTutors)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tutors_list_layout, parent, false)
        holder = ViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name : String = tutors[position].firstName + " " + tutors[position].lastName
        holder.tutorNameView.text = name
        holder.tutorImage.setImageResource(R.drawable.ic_launcher_foreground)
        holder.cardView.setOnClickListener {view: View ->
                // TODO get to information activity of the tutor
        }
    }

    override fun getItemCount(): Int {
        return tutors.size
    }

}