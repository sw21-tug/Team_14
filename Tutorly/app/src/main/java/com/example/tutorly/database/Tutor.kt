package com.example.tutorly.database

import android.media.Image

enum class LvlOfKnowledge{
    SCHOOL, MATURA, UNIVERSITY
}

class Tutor(
    val id: String, val firstName: String, val lastName: String, val email: String,
    val subjectIDs: HashMap<String, LvlOfKnowledge>, val phoneNumber: String, val image: Image? = null)