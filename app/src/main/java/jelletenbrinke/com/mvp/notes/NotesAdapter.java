package jelletenbrinke.com.mvp.notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jelletenbrinke.com.mvp.R;
import jelletenbrinke.com.mvp.data.Note;
import jelletenbrinke.com.mvp.utils.ClickListener;

/**
 * Created by jelle on 11/01/2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private ClickListener clickListener;
    private ArrayList<Note> notes;

    public NotesAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        this.notes = new ArrayList<>();
    }

    public void setNotes(List<Note> newNotes) {
        notes.clear();
        notes.addAll(newNotes);
        notifyDataSetChanged();
    }

    public void addNote(Note note) {
        notes.add(note);
        notifyItemInserted(notes.indexOf(note));
    }

    public void removeNote(Note note) {
        int lastIndex = notes.indexOf(note);
        notes.remove(note);
        notifyItemRemoved(lastIndex);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_note, parent, false);
        ViewHolder holder = new ViewHolder(v, clickListener);
        holder.noteText = (TextView) v.findViewById(R.id.noteText);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = notes.get(position);

        holder.noteText.setText(note.getNote());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView noteText;
        private ClickListener clickListener;

        public ViewHolder(View v, ClickListener clickListener) {
            super(v);
            this.clickListener = clickListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}
