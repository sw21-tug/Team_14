package com.example.tutorly.database

class Tutor(
    _firstName: String, _lastName: String, _email: String, _password: String,
    _subjectIDs: ArrayList<String>, _phoneNumber: String )
    : User(_firstName, _lastName, _email, _password) {

    var subjectIDs = _subjectIDs
    var phoneNumber = _phoneNumber
}