package com.example.tutorly.database

import junit.framework.TestCase
import org.junit.Test

class DatabaseTest : TestCase() {
    var dbObj = Database()

    public override fun setUp() {
        super.setUp()

    }

    public override fun tearDown() {


    }

    @Test
    fun testSubjectList()
    {
        dbObj.getSubjectsList()
    }

    @Test
    fun testTutorList(){
        dbObj.getTutorList()
    }
}