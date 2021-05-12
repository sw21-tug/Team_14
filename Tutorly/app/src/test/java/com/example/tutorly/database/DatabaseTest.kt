package com.example.tutorly.database

import com.example.tutorly.Subject
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.logging.Logger


class DatabaseTest : TestCase() {
    private val log: Logger = Logger.getLogger(DatabaseTest::class.toString())
    @Mock
    lateinit var mockedReference: DatabaseReference
    @Mock
    lateinit var mockedDataSnapshot: DataSnapshot
    @Mock
    lateinit var mockedError: DatabaseError

    private lateinit var database: Database
    private var testVar = 1

    @Before
    public override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        // reference mocking
        `when`(mockedReference.child(anyString())).thenReturn(mockedReference)
        database = Database(mockedReference)

        //DataSnapshot mocking
        //`when`(mockedDataSnapshot.children).thenReturn(mockedDataSnapshot)
    }

    @After
    public override fun tearDown() {
    }

    private fun changeVar(newSubjects: ArrayList<Subject>)
    {
        testVar = 0
        assertTrue(newSubjects.isEmpty())
    }

    @Test
    fun testSubjectList()
    {
        /*
        doAnswer { invocation ->
            val valueEventListener = invocation.arguments[0] as ValueEventListener
            //when(mockedDataSnapshot.getValue(User.class)).thenReturn(testOrMockedUser)
            valueEventListener.onDataChange(mockedDataSnapshot)
            //valueEventListener.onCancelled(...);
            null
        }.`when`(mockedReference).addValueEventListener(any(ValueEventListener::class.java))
         */
        database.getSubjectsList(::changeVar)
        database.dbSubjectListener.onDataChange(mockedDataSnapshot)

        try {
            database.dbSubjectListener.onCancelled(mockedError)
            assertTrue(false)
        }catch (e: Throwable){
            log.info("GOT ERROR")
        }

        assertEquals(0, testVar)
    }

    @Test
    fun testTutorList(){
        //database.getTutorList()
    }
}

