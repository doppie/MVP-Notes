package jelletenbrinke.com.mvp.notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import javax.inject.Inject

import jelletenbrinke.com.mvp.R
import jelletenbrinke.com.mvp.data.Note
import jelletenbrinke.com.mvp.notes.dagger.DaggerNotesComponent
import jelletenbrinke.com.mvp.notes.dagger.NotesPresenterModule
import jelletenbrinke.com.mvp.utils.ClickListener
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity(), NotesContract.View, ClickListener {

    //lazy handles our findViewById and other view setters-> executed once on first time use.
    private val notesList by lazy {
        notes_list.setHasFixedSize(true)
        notes_list.layoutManager = LinearLayoutManager(this)
        notes_list
    }

    private val notesAdapter by lazy {
        NotesAdapter(this)
    }

    //lateinit allows us to define a non-nullable property and set the value later.
    @Inject lateinit var presenter : NotesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        DaggerNotesComponent.builder()
                .notesPresenterModule(NotesPresenterModule(this)).build()
                .inject(this)

        notesList.setAdapter(notesAdapter)
    }

    public override fun onResume() {
        super.onResume()
        presenter.resume()
        presenter.getNotes()
    }

    public override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    override fun showNotes(notes: List<Note>) {
        notesAdapter.setNotes(notes)
    }

    override fun addNoteToListView(note: Note) {
        notesAdapter.removeNote(note)
    }

    override fun removeNoteFromListView(note: Note) {
        notesAdapter.addNote(note)
    }

    override fun showLoadingNotesError() {
        //TODO: show an error.
    }

    override fun onClick(v: View, position: Int, isLongClick: Boolean) {

    }
}
