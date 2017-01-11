package jelletenbrinke.com.mvp.data;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;


/**
 * Created by jelle on 11/01/2017.
 */

public interface NotesDataSource {

    Observable<List<Note>> getNotes();

    void saveNote(@NonNull Note note);

}
