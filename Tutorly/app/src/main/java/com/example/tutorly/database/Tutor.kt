package com.example.tutorly.database

import android.media.Image
import java.io.Serializable

enum class LvlOfKnowledge{
    School, Matura, University
}

class Tutor (
    val id: String, val firstName: String, val lastName: String, val email: String,
    val subjectIDs: HashMap<String, LvlOfKnowledge>, val phoneNumber: String, val image: Image? = null): Serializable
