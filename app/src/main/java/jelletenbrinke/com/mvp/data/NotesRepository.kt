package jelletenbrinke.com.mvp.data

import android.support.annotation.VisibleForTesting

import java.util.ArrayList

import javax.inject.Inject

import jelletenbrinke.com.mvp.data.local.NotesLocalDataSource
import rx.Observable
import rx.functions.Func1

/**
 * Created by jelle on 11/01/2017.
 * Singleton class
 */
class NotesRepository
/**
 * Prevent direct instantiation
 */
internal constructor(private val notesLocalDataSource: NotesDataSource) : NotesDataSource {

    //caching our retrieved notes.
    private var cachedNotes: ArrayList<Note> = ArrayList()

    override fun getNotes(): Observable<List<Note>> {
        //first check our cache.
        if (!cachedNotes.isEmpty()) {
            return Observable.from(cachedNotes).toList()
        } else {
            cachedNotes = ArrayList<Note>()
        }

        //otherwise reach out to our local data source.
        val localNotes = notesLocalDataSource.getNotes()
                .flatMap { notes -> cacheNotes(notes); Observable.from(notes).toList() }
        return localNotes
    }

    fun cacheNotes(notes : List<Note>) {
        cachedNotes.addAll(notes);
    }

    override fun saveNote(note: Note) {

    }

    companion object {

        private var instance: NotesRepository? = null

        fun getInstance(notesLocalDataSource: NotesDataSource): NotesRepository {
            if (instance == null) {
                instance = NotesRepository(notesLocalDataSource)
            }
            return instance as NotesRepository
        }

        /**
         * Used to reset the repository in unit-tests.
         */
        fun destroyInstance() {
            instance = null
        }
    }
}
