package com.example.tutorly.database

import android.util.Log
import com.example.tutorly.Subject
import com.google.firebase.database.*


class Database constructor(private val reference: DatabaseReference = FirebaseDatabase.getInstance().reference)
{
    val subject_list = ArrayList<Subject>()
    val tutor_list = ArrayList<Tutor>()

    fun getSubjectsList(updateUI: (newSubjects: ArrayList<Subject>) -> Unit) {
        val subjects_ref = reference.child("subjects")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for(subject_data in dataSnapshot.children)
                {
                    if(subject_data.key == null || subject_data.value == null)
                        continue
                    var id = ""
                    var desc = ""
                    for(child in subject_data.children)
                    {
                        if(child.key == null || child.value == null)
                            continue

                        if(child.key == "id")
                        {
                            id = child.value as String
                        }

                        if(child.key == "description")
                        {
                            desc = child.value as String
                        }
                    }
                    val subject = Subject(id, subject_data.key as String, desc)
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

    fun getTutorList(updateUI: (newTutors: ArrayList<Tutor>) -> Unit) {
        val tutors_ref = reference.child("tutors")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for(tutor_data in dataSnapshot.children)
                {
                    if(tutor_data.key == null || tutor_data.value == null)
                        continue
                    val subjectIDs = ArrayList<String>()
                    var id = tutor_data.key
                    for(child in tutor_data.children)
                    {
                        if(child.key == null || child.value == null)
                            continue
                        subjectIDs.add(child.key as String)

                    }
                    //TODO: retrieve user data of a tutor
                    val tutor = Tutor("Ema", "Salkic", "ema@gmail.com", "123456",
                                       subjectIDs, "123456789")
                    println("Adding: " + tutor)
                    tutor_list.add(tutor)
                }
                updateUI(tutor_list)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.println(Log.INFO, "loadPost:onCancelled",
                    databaseError.toException().toString()
                )
            }
        }
        tutors_ref.addValueEventListener(postListener)
    }



}