package jelletenbrinke.com.mvp.data

import rx.Observable


/**
 * Created by jelle on 11/01/2017.
 */

interface NotesDataSource {

    fun getNotes() : Observable<List<Note>>

    fun saveNote(note: Note)

}
