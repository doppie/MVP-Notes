package jelletenbrinke.com.mvp.data;

import java.util.Calendar;

/**
 * Created by jelle on 11/01/2017.
 */
public class Note {

    private long id;
    private String note;
    private Calendar date;

    public Note(long id, String note, Calendar date) {
        this.id = id;
        this.note = note;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public Calendar getDate() {
        return date;
    }
}
