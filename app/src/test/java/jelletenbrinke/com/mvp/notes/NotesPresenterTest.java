package jelletenbrinke.com.mvp.notes;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jelletenbrinke.com.mvp.data.Note;
import jelletenbrinke.com.mvp.data.NotesRepository;
import jelletenbrinke.com.mvp.utils.schedulers.BaseSchedulerProvider;
import jelletenbrinke.com.mvp.utils.schedulers.ImmediateSchedulerProvider;
import jelletenbrinke.com.mvp.utils.schedulers.SchedulerProvider;
import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jelle on 11/01/2017.
 *
 * Unit test for the implementation of {@link NotesPresenter}
 */
public class NotesPresenterTest {

    @Mock
    private NotesRepository notesRepository;
    @Mock
    private NotesContract.View notesView;

    private BaseSchedulerProvider schedulerProvider;
    private NotesPresenter notesPresenter;
    private static List<Note> notes;

    @Before
    public void setupTasksPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        //We want immediate results, we cannot use the android threads in normal java unit tests.
        schedulerProvider = new ImmediateSchedulerProvider();

//        notesPresenter = new NotesPresenter(notesView, notesRepository, schedulerProvider);

        notes = new ArrayList<>();
        notes.add(new Note(1, "Test Note 1", Calendar.getInstance()));
        notes.add(new Note(2, "Test Note 2", Calendar.getInstance()));
        notes.add(new Note(3, "Test Note 3", Calendar.getInstance()));
    }

    @Test
    public void loadAllNotesFromRepositoryIntoView() {

        //we will return our notes list when notesRepository.getNotes is called.
        when(notesRepository.getNotes()).thenReturn(Observable.just(notes));

        //retrieve the notes
        notesPresenter.getNotes();

        //check if our view is updated with expected list.
        verify(notesView).showNotes(notes);
    }

    @Test
    public void errorLoadingNotes_ShowsError() {
        //we will return an Exception when notesRepository.getNotes is called.
        //Note: This could be more specific to catch certain errors
        //by throwing a more specific Exception. (for example: IOException)
        when(notesRepository.getNotes()).thenReturn(Observable.<List<Note>>error(new Exception()));

        //retrieve the notes
        notesPresenter.getNotes();

        //fail and make sure an error is shown.
        verify(notesView).showLoadingNotesError();
    }

}
