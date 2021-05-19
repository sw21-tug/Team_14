package com.example.tutorly.database

enum class LvlOfKnowledge{
    SCHOOL, MATURA, UNIVERSITY
}

class Tutor(
    val firstName: String, val lastName: String, val email: String,
    val subjectIDs: HashMap<String, LvlOfKnowledge>, val phoneNumber: String )