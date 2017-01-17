package jelletenbrinke.com.mvp;

import android.app.Application;

import jelletenbrinke.com.mvp.data.DaggerNotesRepositoryComponent;
import jelletenbrinke.com.mvp.data.NotesRepositoryComponent;

/**
 * Created by jelle on 16/01/2017.
 */

public class BaseApplication extends Application {

    private NotesRepositoryComponent repositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        repositoryComponent = DaggerNotesRepositoryComponent.builder().build();
    }

    public NotesRepositoryComponent getNotesRepositoryComponent() {
        return repositoryComponent;
    }
}
