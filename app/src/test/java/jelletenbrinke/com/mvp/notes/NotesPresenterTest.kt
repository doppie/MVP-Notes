package jelletenbrinke.com.mvp.notes

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import java.util.ArrayList
import java.util.Calendar

import jelletenbrinke.com.mvp.data.Note
import jelletenbrinke.com.mvp.data.NotesRepository
import jelletenbrinke.com.mvp.utils.schedulers.BaseSchedulerProvider
import jelletenbrinke.com.mvp.utils.schedulers.ImmediateSchedulerProvider
import jelletenbrinke.com.mvp.utils.schedulers.SchedulerProvider
import rx.Observable

import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

/**
 * Created by jelle on 11/01/2017.

 * Unit test for the implementation of [NotesPresenter]
 */
class NotesPresenterTest {

    @Mock
    private val notesRepository: NotesRepository? = null
    @Mock
    private val notesView: NotesContract.View? = null

    private var schedulerProvider: BaseSchedulerProvider? = null
    private var notesPresenter: NotesPresenter? = null

    @Before
    fun setupTasksPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        //We want immediate results, we cannot use the android threads in normal java unit tests.
        schedulerProvider = ImmediateSchedulerProvider()

        notesPresenter = NotesPresenter(notesView, notesRepository, schedulerProvider)

        notes = ArrayList<Note>()
        notes!!.add(Note(1, "Test Note 1", Calendar.getInstance()))
        notes!!.add(Note(2, "Test Note 2", Calendar.getInstance()))
        notes!!.add(Note(3, "Test Note 3", Calendar.getInstance()))
    }

    @Test
    fun loadAllNotesFromRepositoryIntoView() {

        //we will return our notes list when notesRepository.getNotes is called.
        `when`(notesRepository!!.notes).thenReturn(Observable.just<List<Note>>(notes))

        //retrieve the notes
        notesPresenter!!.getNotes()

        //check if our view is updated with expected list.
        verify<NotesContract.View>(notesView).showNotes(notes)
    }

    @Test
    fun errorLoadingNotes_ShowsError() {
        //we will return an Exception when notesRepository.getNotes is called.
        //Note: This could be more specific to catch certain errors
        //by throwing a more specific Exception. (for example: IOException)
        `when`(notesRepository!!.notes).thenReturn(Observable.error<List<Note>>(Exception()))

        //retrieve the notes
        notesPresenter!!.getNotes()

        //fail and make sure an error is shown.
        verify<NotesContract.View>(notesView).showLoadingNotesError()
    }

    companion object {
        private var notes: MutableList<Note>? = null
    }

}
