package com.example.tutorly.database

open class User (_firstName : String, _lastName : String, _email : String, _password : String) {

    private var userID : String? = null

    private var firstName : String= _firstName.toLowerCase().capitalize()
    private var lastName = _lastName.toLowerCase().capitalize()
    private var email = _email
    private var password = _password

    fun getEmail(): String {
        return email
    }

    fun getpassword(): String {
        return password
    }

    fun setUserID(_userID: String) {
        userID = _userID
    }

    fun getName(): String{
        return firstName
    }

    fun getSurname(): String{
        return lastName
    }

}
