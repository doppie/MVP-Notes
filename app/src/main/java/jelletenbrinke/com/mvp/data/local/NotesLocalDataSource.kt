package jelletenbrinke.com.mvp.data.local

import java.util.ArrayList
import java.util.Calendar

import jelletenbrinke.com.mvp.data.Note
import jelletenbrinke.com.mvp.data.NotesDataSource
import rx.Observable

/**
 * Created by jelle on 11/01/2017.
 */

class NotesLocalDataSource
//TODO: We could implement a database here as a source.

internal constructor() : NotesDataSource {

    private var notes: ArrayList<Note> = ArrayList()

    init {
        //instantiate our data source.
        //for the demo we provide a dummy list instead.
        loadDummyList()
    }

    override fun getNotes(): Observable<List<Note>> {
        return Observable.from(notes!!).toList()
    }

    override fun saveNote(note: Note) {

    }

    private fun loadDummyList() {
        notes = ArrayList<Note>()
        notes.add(Note(1, "1. The view asks the presenter for notes.", Calendar.getInstance()))
        notes.add(Note(2, "2. Presenter creates a subscription on NotesRepository.getNotes().", Calendar.getInstance()))
        notes.add(Note(3, "3. NotesRepository asks his data sources for data.", Calendar.getInstance()))
        notes.add(Note(4, "4. Data is cached in NotesRepository so next time it will be available immediately.", Calendar.getInstance()))
        notes.add(Note(5, "5. Presenter received the requested list", Calendar.getInstance()))
        notes.add(Note(6, "6. Presenter updates view with the list", Calendar.getInstance()))
        notes.add(Note(7, "7. View shows the user the list of notes.", Calendar.getInstance()))
        notes.add(Note(8, "Tip: Don't forget the error handling in the presenter.", Calendar.getInstance()))
        notes.add(Note(9, "Tip: Make sure you create tests for all layers.", Calendar.getInstance()))
        notes.add(Note(10, "Tip: If you don't know where to put code, talk with people.", Calendar.getInstance()))
        notes.add(Note(11, "Tip: Coding can be tough, sometimes it helps to draw the flow first and discuss this with your friendly colleagues :)", Calendar.getInstance()))
        notes.add(Note(12, "Tip: Separating code in small methods (<=5 lines) helps keeping code readable, but also allows testing to be better and easier.", Calendar.getInstance()))
    }

    companion object {

        private var theInstance: NotesLocalDataSource? = null

        fun getInstance(): NotesLocalDataSource {
            if (theInstance == null) {
                theInstance = NotesLocalDataSource()
            }
            return theInstance as NotesLocalDataSource
        }
    }

}
