package com.example.tutorly.database

import android.util.Log
import com.example.tutorly.Subject
import com.google.firebase.database.*


class Database constructor(private val reference: DatabaseReference = FirebaseDatabase.getInstance().reference)
{
    val subject_list = ArrayList<Subject>()

    fun getSubjectsList(updateUI: (newSubjects: ArrayList<Subject>) -> Unit) {
        val subjects_ref = reference.child("subjects")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for(child in dataSnapshot.children)
                {
                    if(child.key == null || child.value == null)
                    {
                        continue
                    }
                    val subject = Subject(child.key as String, child.value as String)
                    println("Adding: " + subject.name)
                    subject_list.add(subject)
                }
                updateUI(subject_list)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.println(Log.INFO, "loadPost:onCancelled",
                    databaseError.toException().toString()
                )
            }
        }
        subjects_ref.addValueEventListener(postListener)
    }

    fun getTutorList() {
        TODO("Not yet implemented")
    }



}