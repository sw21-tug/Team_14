package com.example.tutorly.database

import com.android.volley.Response
import com.example.tutorly.Subject
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import junit.framework.TestCase
import net.bytebuddy.agent.builder.AgentBuilder
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.logging.Logger


class DatabaseTest : TestCase() {
    private val log: Logger = Logger.getLogger(DatabaseTest::class.toString())
    @Mock
    lateinit var mockedReference: DatabaseReference
    @Mock
    lateinit var mockedDataSnapshot: DataSnapshot
    @Mock
    lateinit var mockedError: DatabaseError
    @Mock
    lateinit var mockedTask: Task<Void>

    private lateinit var database: Database
    private var testVar = 1

    private val outContent: ByteArrayOutputStream = ByteArrayOutputStream()
    private val originalOut: PrintStream = System.out

    @Before
    public override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        // reference mocking
        `when`(mockedReference.child(anyString())).thenReturn(mockedReference)
        database = Database(mockedReference)

        System.setOut(PrintStream(outContent));

        `when`(mockedReference.setValue(any())).thenReturn(mockedTask)
    }

    @After
    public override fun tearDown() {
        System.setOut(originalOut);
    }

    private fun changeVar(newSubjects: ArrayList<Subject>)
    {
        testVar = 0
        assertTrue(newSubjects.isEmpty())
    }

    private fun changeVarTutors(newTutors: ArrayList<Tutor>)
    {
        testVar = 0
        assertTrue(newTutors.isEmpty())
    }

    @Test
    fun testSubjectList()
    {
        database.getSubjectsList(::changeVar)
        database.dbSubjectListener.onDataChange(mockedDataSnapshot)

        try {
            database.dbSubjectListener.onCancelled(mockedError)
            assertTrue(false)
        }catch (e: Throwable){
            log.info("GOT ERROR")
        }

        assertEquals(0, testVar)

        testVar = 1
    }

    @Test
    fun testTutorList(){
        database.getTutorList(::changeVarTutors)
        database.dbTutorsListener.onDataChange(mockedDataSnapshot)

        try {
            database.dbTutorsListener.onCancelled(mockedError)
            assertTrue(false)
        }catch (e: Throwable){
            log.info("GOT ERROR")
        }

        assertEquals(0, testVar)

        testVar = 1
    }
}

