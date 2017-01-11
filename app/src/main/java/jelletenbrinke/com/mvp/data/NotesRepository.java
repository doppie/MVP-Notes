package jelletenbrinke.com.mvp.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import jelletenbrinke.com.mvp.data.local.NotesLocalDataSource;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jelle on 11/01/2017.
 * Singleton class
 */
public class NotesRepository implements NotesDataSource {

    @Nullable
    private static NotesRepository instance;

    @NonNull
    private NotesDataSource notesLocalDataSource;

    //caching our retrieved notes.
    private ArrayList<Note> cachedNotes;

    /**
     * Prevent direct instantiation
     */
    private NotesRepository() {
        notesLocalDataSource = NotesLocalDataSource.getInstance();
    }

    public static NotesRepository getInstance() {
        if(instance == null) {
            instance = new NotesRepository();
        }
        return instance;
    }


    @Override
    public Observable<List<Note>> getNotes() {
        //first check our cache.
        if(cachedNotes != null && !cachedNotes.isEmpty()) {
            return Observable.from(cachedNotes).toList();
        } else {
            cachedNotes = new ArrayList<>();
        }

        //otherwise reach out to our local data source.
        Observable<List<Note>> localNotes = getAndCacheLocalNotes();
        return localNotes;
    }

    private Observable<List<Note>> getAndCacheLocalNotes() {
        return notesLocalDataSource.getNotes()
                .flatMap(new Func1<List<Note>, Observable<List<Note>>>() {
                    @Override
                    public Observable<List<Note>> call(List<Note> notes) {
                        return Observable.from(notes)
                                .doOnNext(note -> {
                                    cachedNotes.add(note);
                                }).toList();
                    }
                });
    }

    @Override
    public void saveNote(@NonNull Note note) {

    }
}
