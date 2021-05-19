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

        //DataSnapshot mocking
        //`when`(mockedDataSnapshot.children).thenReturn(mockedDataSnapshot)

        `when`(mockedReference.setValue(any())).thenReturn(mockedTask)
        //mockedTask.addOnSuccessListener { println("added tutor") }
        //mockedTask.addOnFailureListener { println("not added tutor") }
        //verify(mockedTask).addOnSuccessListener { println("added tutor") }
        //verify(mockedTask).addOnFailureListener { println("not added tutor") }

        //`when`(mockedReference.setValue(any()).addOnSuccessListener { println("added tutor") })
        //`when`(mockedReference.setValue(any()).addOnFailureListener { println("not added tutor") })
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

        testVar = 1
    }

    @Test
    fun testTutorList(){
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

    @Test
    fun testAddingTutor() {
        val hashmap = hashMapOf("German" to LvlOfKnowledge.MATURA, "English" to LvlOfKnowledge.SCHOOL, "Maths" to LvlOfKnowledge.UNIVERSITY)
        val tutor = Tutor("3", "hai", "uzg", "1@6.4", hashmap, "147801")
        //database.addTutor(tutor)
        // TODO find a way to solve this lol

        assertTrue(outContent.toString().equals("added tutor\n") || outContent.toString().equals("not added tutor\n"))
    }
}

