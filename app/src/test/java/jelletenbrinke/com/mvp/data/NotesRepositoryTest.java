package jelletenbrinke.com.mvp.data;

/**
 * Created by jelle on 11/01/2017.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of the in-memory repository with cache.
 */
public class NotesRepositoryTest {

    @Mock
    private NotesDataSource notesLocalDataSource;

    private NotesRepository notesRepository;
    private TestSubscriber<List<Note>> notesTestSubscriber;
    private ArrayList<Note> notes;

    @Before
    public void setupNotesRepository() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        notesRepository = NotesRepository.getInstance(notesLocalDataSource);

        notesTestSubscriber = new TestSubscriber<>();

        notes = new ArrayList<>();
        notes.add(new Note(1, "Test Note 1", Calendar.getInstance()));
        notes.add(new Note(2, "Test Note 2", Calendar.getInstance()));
        notes.add(new Note(3, "Test Note 3", Calendar.getInstance()));
    }

    @After
    public void destroyRepositoryInstance() {
        NotesRepository.destroyInstance();
    }

    @Test
    public void getTasks_RepositoryCachesAfterFirstSubscription_whenTasksAvailableInLocalDataSource() {
        //make sure our local data source has data
        setNotesAvailable(notesLocalDataSource, notes);

        //TIP: in case we have more data sources, make sure these are unavailable.

        //We need two test subscribers to test if our data is cached when
        //requested from local data source.
        TestSubscriber<List<Note>> testSubscriber1 = new TestSubscriber<>();
        notesRepository.getNotes().subscribe(testSubscriber1);

        TestSubscriber<List<Note>> testSubscriber2 = new TestSubscriber<>();
        notesRepository.getNotes().subscribe(testSubscriber2);

        //we want our local data source to be called only once to verify we received the cache instead.
        verify(notesLocalDataSource, times(1)).getNotes();
    }

    @Test
    public void getTasks_noCachingDataAvailable() {
        setNotesAvailable(notesLocalDataSource, notes);

        TestSubscriber<List<Note>> testSubscriber = new TestSubscriber<>();
        notesRepository.getNotes().subscribe(testSubscriber);

        verify(notesLocalDataSource).getNotes();
    }

    //TODO: Test what happens if there is no data available.

    private void setNotesAvailable(NotesDataSource dataSource, List<Note> notes) {
        when(dataSource.getNotes()).thenReturn(Observable.<List<Note>>just(notes) //return the list
                .concatWith(Observable.<List<Note>>never())); //never allow to complete.
    }

    private void setNotesUnavailable(NotesDataSource dataSource) {
        when(dataSource.getNotes()).thenReturn(Observable.just(Collections.<Note>emptyList())); //return an empty list when called.
    }
}
