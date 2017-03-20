package jelletenbrinke.com.mvp.notes

import jelletenbrinke.com.mvp.BasePresenter
import jelletenbrinke.com.mvp.BaseView
import jelletenbrinke.com.mvp.data.Note

/**
 * Created by jelle on 11/01/2017.
 * Specifies the contract between View and Presenter.
 */
interface NotesContract {

    interface View : BaseView<Presenter> {

        /**
         * Shows a list of notes in the RecyclerView
         */
        fun showNotes(notes: List<Note>)

        /**
         * Adds a note to the RecyclerView
         * @param note
         */
        fun addNoteToListView(note: Note)

        /**
         * Removes a note from the RecyclerView
         * @param note
         */
        fun removeNoteFromListView(note: Note)

        fun showLoadingNotesError()

    }

    interface Presenter : BasePresenter {

        /**
         * Calls the repository to get our notes from a datasource.
         */
        fun getNotes()
    }
}
