package com.example.tutorly.database

import android.media.Image

enum class LvlOfKnowledge{
    School, Matura, University
}

class Tutor(
    val id: String, val firstName: String, val lastName: String, val email: String,
    val subjectIDs: HashMap<String, LvlOfKnowledge>, val phoneNumber: String, val address: String = "", val image: Image? = null)