package com.example.tutorly

import android.media.Image

public class Tutor constructor(val name: String, val id: String, val image: Image? = null) {

    var tutor_name: String = name
    var tutor_id: String = id
    var tutor_image: Image? = image

}
