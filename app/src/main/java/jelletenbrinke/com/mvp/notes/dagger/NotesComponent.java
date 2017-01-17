package jelletenbrinke.com.mvp.notes.dagger;

import dagger.Component;
import jelletenbrinke.com.mvp.notes.NotesActivity;
import jelletenbrinke.com.mvp.notes.dagger.NotesPresenterModule;

/**
 * Created by jelle on 16/01/2017.
 */
@Component(modules = NotesPresenterModule.class)
public interface NotesComponent {

    void inject(NotesActivity activity);

}
