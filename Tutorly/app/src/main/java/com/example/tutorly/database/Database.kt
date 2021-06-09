package com.example.tutorly.database

import android.content.res.Resources
import android.util.Log
import com.example.tutorly.Subject
import com.example.tutorly.Translation
import com.example.tutorly.Translation.locale
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.reflect.KFunction1


class Database constructor(private val reference: DatabaseReference = FirebaseDatabase.getInstance().reference)
{
    val subject_list = ArrayList<Subject>()
    val tutor_list = ArrayList<Tutor>()
    lateinit var dbSubjectListener: ValueEventListener
    lateinit var dbTutorsListener: ValueEventListener

    fun getSubjectsList(updateUI: (newSubjects: ArrayList<Subject>) -> Unit) {
        var current_language = Locale.getDefault().language;
        print(current_language)
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
                    var descRU = ""
                    var subject_name = ""
                    var subject_nameRU = ""
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

                        if(child.key == "descriptionRU")
                        {
                            descRU = child.value as String
                        }

                        if(child.key == "name")
                        {
                            subject_name = child.value as String
                        }

                        if(child.key == "nameRU")
                        {
                            subject_nameRU = child.value as String
                        }
                    }
                    val subject: Subject
                    //if (Locale.getDefault().language.equals(Locale("en").language))
                    if (Locale.getDefault().language.equals("en"))
                    {
                        subject = Subject(id, subject_name, desc)
                    }
                    else
                    {
                        subject = Subject(id, subject_nameRU, descRU)
                    }

                    println("Check: " + Translation.locale)
                    println("Adding: " + subject.name)
                    subject_list.add(subject)
                }
                updateUI(subject_list)
                subject_list.clear()
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
        dbTutorsListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for(tutor_data in dataSnapshot.children)
                {
                    if(tutor_data.key == null || tutor_data.value == null)
                        continue
                    val subjectIDs = HashMap<String, LvlOfKnowledge>()
                    val userID = tutor_data.key as String
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
                                subjectIDs[subject.key as String] = LvlOfKnowledge.valueOf(subject.value.toString())
                            }
                        }
                        if(tutorInfo.key == "name")
                            name = tutorInfo.value as String
                        else if(tutorInfo.key == "surname")
                            surname = tutorInfo.value as String
                        else if(tutorInfo.key == "phone")
                            phone = tutorInfo.value as String
                        else if(tutorInfo.key == "email")
                            email = tutorInfo.value as String
                    }
                    val tutor = Tutor(userID, name, surname, email, subjectIDs, phone)
                    tutor_list.add(tutor)
                }
                updateUI(tutor_list)
                tutor_list.clear()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.println(Log.INFO, "loadPost:onCancelled",
                    databaseError.toException().toString()
                )
            }
        }
        tutors_ref.addValueEventListener(dbTutorsListener)
    }

    fun addTutor(tutor: Tutor){
        val tutors_ref = reference.child("tutors")
        val tutorsSubjects = HashMap<String, LvlOfKnowledge>()
        for(subject in tutor.subjectIDs)
        {
            tutorsSubjects[subject.key] = subject.value
        }
        val map: HashMap<String, Any> = hashMapOf(
            "email" to tutor.email,
            "name" to tutor.firstName,
            "surname" to tutor.lastName,
            "phone" to tutor.phoneNumber,
            "subjects" to tutorsSubjects
        )
        val userID = tutor.id
        tutors_ref.child(userID)
            .setValue(map)
            .addOnSuccessListener { println("added tutor") }
            .addOnFailureListener { println("failed to add new tutor") }
    }
}