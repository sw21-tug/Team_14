package com.example.tutorly

import android.media.Image

public class Subject constructor(val name: String, val desc: String, val image: Image? = null) {
    var subjectName: String = name
    var subjectDesc: String = desc
    var subjectImage: Image? = image
    var isSelected: Boolean = false
}