package com.example.tutorly

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorly.database.LvlOfKnowledge
import com.example.tutorly.database.Tutor
import com.google.android.material.card.MaterialCardView

class TutorsRecyclerView(private val context: Context, private val tutors: ArrayList<Tutor>) : RecyclerView.Adapter<TutorsRecyclerView.ViewHolder>() {

    lateinit var holder: ViewHolder
    private val fullTutorList = ArrayList<Tutor>()
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
        fullTutorList.clear()
        fullTutorList.addAll(newTutors)
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
            val intent = Intent(context, TutorProfile::class.java)
            intent.putExtra("Tutor",  tutors[holder.adapterPosition])
            context.startActivity(intent);

        }
    }

    override fun getItemCount(): Int {
        return tutors.size
    }

    fun updateFilteredList(selectedSubjects: ArrayList<String>, selectedLevelOfKnowledge: String) {
        println("FUll list: $fullTutorList")
        val filteredTutors = ArrayList<Tutor>()
        for(tutor in tutors)
        {
            val tutorSubjects = ArrayList(tutor.subjectIDs.keys)
            val intersectSubjects = tutorSubjects.intersect(selectedSubjects)

            for(key in intersectSubjects)
            {
                val tutorLvlOfKnowledge = tutor.subjectIDs[key]
                if(tutorLvlOfKnowledge != null && tutorLvlOfKnowledge >= LvlOfKnowledge.valueOf(selectedLevelOfKnowledge))
                    filteredTutors.add(tutor)
            }
        }
        if(filteredTutors.isNotEmpty())
        {
            tutors.clear()
            tutors.addAll(filteredTutors)
        }
        else
        {
            tutors.clear()
            tutors.addAll(fullTutorList)
        }
        notifyDataSetChanged()
    }

}