package jelletenbrinke.com.mvp.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import jelletenbrinke.com.mvp.R;
import jelletenbrinke.com.mvp.data.Note;
import jelletenbrinke.com.mvp.utils.ClickListener;

public class NotesActivity extends AppCompatActivity implements NotesContract.View, ClickListener {


    private RecyclerView notesList;

    private NotesContract.Presenter presenter;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        initNotesList();

        presenter = new NotesPresenter(this);
        presenter.getNotes();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    private void initNotesList() {
        notesList = (RecyclerView) findViewById(R.id.list);
        notesList.setHasFixedSize(true);
        notesList.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter = new NotesAdapter(this);
        notesList.setAdapter(notesAdapter);
    }

    @Override
    public void showNotes(List<Note> notes) {
        notesAdapter.setNotes(notes);
    }

    @Override
    public void addNoteToListView(Note note) {
        notesAdapter.removeNote(note);
    }

    @Override
    public void removeNoteFromListView(Note note) {
        notesAdapter.addNote(note);
    }

    @Override
    public void onClick(View v, int position, boolean isLongClick) {

    }
}
