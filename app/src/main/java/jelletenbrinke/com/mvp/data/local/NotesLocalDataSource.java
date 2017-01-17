package jelletenbrinke.com.mvp.data.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jelletenbrinke.com.mvp.data.Note;
import jelletenbrinke.com.mvp.data.NotesDataSource;
import rx.Observable;

/**
 * Created by jelle on 11/01/2017.
 */

public class NotesLocalDataSource implements NotesDataSource {

    @Nullable
    private static NotesLocalDataSource instance;

    private ArrayList<Note> notes;

    //TODO: We could implement a database here as a source.

    private NotesLocalDataSource() {
        //instantiate our data source.
        //for the demo we provide a dummy list instead.
        loadDummyList();
    }

    public static NotesLocalDataSource getInstance() {
        if(instance == null) {
            instance = new NotesLocalDataSource();
        }
        return instance;
    }

    @Override
    public Observable<List<Note>> getNotes() {
        return Observable.from(notes).toList();
    }

    @Override
    public void saveNote(@NonNull Note note) {

    }

    private void loadDummyList() {
        notes = new ArrayList<>();
        notes.add(new Note(1, "1. The view asks the presenter for notes.", Calendar.getInstance()));
        notes.add(new Note(2, "2. Presenter creates a subscription on NotesRepository.getNotes().", Calendar.getInstance()));
        notes.add(new Note(3, "3. NotesRepository asks his data sources for data.", Calendar.getInstance()));
        notes.add(new Note(4, "4. Data is cached in NotesRepository so next time it will be available immediately.", Calendar.getInstance()));
        notes.add(new Note(5, "5. Presenter received the requested list", Calendar.getInstance()));
        notes.add(new Note(6, "6. Presenter updates view with the list", Calendar.getInstance()));
        notes.add(new Note(7, "7. View shows the user the list of notes.", Calendar.getInstance()));
        notes.add(new Note(8, "Tip: Don't forget the error handling in the presenter.", Calendar.getInstance()));
        notes.add(new Note(9, "Tip: Make sure you create tests for all layers.", Calendar.getInstance()));
        notes.add(new Note(10, "Tip: If you don't know where to put code, talk with people.", Calendar.getInstance()));
        notes.add(new Note(11, "Tip: Coding can be tough, sometimes it helps to draw the flow first and discuss this with your friendly colleagues :)", Calendar.getInstance()));
        notes.add(new Note(12, "Tip: Separating code in small methods (<=5 lines) helps keeping code readable, but also allows testing to be better and easier.", Calendar.getInstance()));
    }

}
