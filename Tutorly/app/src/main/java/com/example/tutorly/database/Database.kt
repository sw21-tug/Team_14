package com.example.tutorly.database

import android.util.Log
import com.example.tutorly.Subject
import com.google.firebase.database.*


class Database constructor(private val reference: DatabaseReference = FirebaseDatabase.getInstance().reference)
{
    val subject_list = ArrayList<Subject>()
    val tutor_list = ArrayList<Tutor>()
    lateinit var dbSubjectListener: ValueEventListener

    fun getSubjectsList(updateUI: (newSubjects: ArrayList<Subject>) -> Unit) {
        val subjects_ref = reference.child("subjects")
        dbSubjectListener = object : ValueEventListener {
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
        subjects_ref.addValueEventListener(dbSubjectListener)
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
                    var email = ""
                    var name = ""
                    var surname = ""
                    var phone = ""
                    for(tutorInfo in tutor_data.children)
                    {
                        if(tutorInfo.key == null || tutorInfo.value == null)
                            continue

                        if(tutorInfo.key == "subjects")
                        {
                            for(subject in tutorInfo.children)
                            {
                                if(subject.key == null || subject.value == null)
                                    continue
                                subjectIDs.add(tutorInfo.key as String)
                                //TODO add value as level of knowledge
                            }
                        }
                        if(tutorInfo.key == "name")
                            name = tutorInfo.value as String
                        else if(tutorInfo.key == "surname")
                            surname = tutorInfo.value as String
                        else if(tutorInfo.key == "email")
                            email = tutorInfo.value as String
                        else if(tutorInfo.key == "phone")
                            phone = tutorInfo.value as String
                    }
                    //TODO: retrieve user password
                    val tutor = Tutor(name, surname, email, "pass", subjectIDs, phone)
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

    fun addTutor(user: User){
        //val tutor = Tutor(user.name, user)
        //TODO push to DB
    }



}