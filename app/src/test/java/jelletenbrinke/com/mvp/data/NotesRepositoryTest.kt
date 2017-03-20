package jelletenbrinke.com.mvp.data

/**
 * Created by jelle on 11/01/2017.
 */

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import java.util.ArrayList
import java.util.Calendar
import java.util.Collections

import rx.Observable
import rx.observers.TestSubscriber

import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

/**
 * Unit tests for the implementation of the in-memory repository with cache.
 */
class NotesRepositoryTest {

    @Mock
    private val notesLocalDataSource: NotesDataSource? = null

    private var notesRepository: NotesRepository? = null
    private var notesTestSubscriber: TestSubscriber<List<Note>>? = null
    private var notes: ArrayList<Note>? = null

    @Before
    fun setupNotesRepository() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        notesRepository = NotesRepository.getInstance(notesLocalDataSource)

        notesTestSubscriber = TestSubscriber<List<Note>>()

        notes = ArrayList<Note>()
        notes!!.add(Note(1, "Test Note 1", Calendar.getInstance()))
        notes!!.add(Note(2, "Test Note 2", Calendar.getInstance()))
        notes!!.add(Note(3, "Test Note 3", Calendar.getInstance()))
    }

    @After
    fun destroyRepositoryInstance() {
        NotesRepository.destroyInstance()
    }

    @Test
    fun getTasks_RepositoryCachesAfterFirstSubscription_whenTasksAvailableInLocalDataSource() {
        //make sure our local data source has data
        setNotesAvailable(notesLocalDataSource!!, notes!!)

        //TIP: in case we have more data sources, make sure these are unavailable.

        //We need two test subscribers to test if our data is cached when
        //requested from local data source.
        val testSubscriber1 = TestSubscriber<List<Note>>()
        notesRepository!!.notes.subscribe(testSubscriber1)

        val testSubscriber2 = TestSubscriber<List<Note>>()
        notesRepository!!.notes.subscribe(testSubscriber2)

        //we want our local data source to be called only once to verify we received the cache instead.
        verify<NotesDataSource>(notesLocalDataSource, times(1)).notes
    }

    @Test
    fun getTasks_noCachingDataAvailable() {
        setNotesAvailable(notesLocalDataSource!!, notes!!)

        val testSubscriber = TestSubscriber<List<Note>>()
        notesRepository!!.notes.subscribe(testSubscriber)

        verify<NotesDataSource>(notesLocalDataSource).notes
    }


    @Test
    fun getTasks_noDataAvailable() {
        setNotesUnavailable(notesLocalDataSource!!)

        val testSubscriber = TestSubscriber<List<Note>>()
        notesRepository!!.notes.subscribe(testSubscriber)
        testSubscriber.assertValue(ArrayList<Note>())
    }
    //TODO: Test what happens if there is no data available.

    private fun setNotesAvailable(dataSource: NotesDataSource, notes: List<Note>) {
        `when`(dataSource.notes).thenReturn(Observable.just(notes) //return the list
                .concatWith(Observable.never<List<Note>>())) //never allow to complete.
    }

    private fun setNotesUnavailable(dataSource: NotesDataSource) {
        `when`(dataSource.notes).thenReturn(Observable.just(emptyList<Note>())) //return an empty list when called.
    }
}
