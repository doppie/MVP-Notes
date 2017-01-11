package jelletenbrinke.com.mvp.notes;

import java.util.List;

import jelletenbrinke.com.mvp.BasePresenter;
import jelletenbrinke.com.mvp.BaseView;
import jelletenbrinke.com.mvp.data.Note;

/**
 * Created by jelle on 11/01/2017.
 * Specifies the contract between View and Presenter.
 */
public interface NotesContract {

    interface View extends BaseView<Presenter> {

        /**
         * Shows a list of notes in the RecyclerView
         */
        void showNotes(List<Note> notes);

        /**
         * Adds a note to the RecyclerView
         * @param note
         */
        void addNoteToListView(Note note);

        /**
         * Removes a note from the RecyclerView
         * @param note
         */
        void removeNoteFromListView(Note note);

        void showLoadingNotesError();

    }

    interface Presenter extends BasePresenter {

        /**
         * Calls the repository to get our notes from a datasource.
         */
        void getNotes();
    }
}
