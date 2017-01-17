package jelletenbrinke.com.mvp.notes;

import dagger.Component;

/**
 * Created by jelle on 16/01/2017.
 */
@Component(modules = NotesPresenterModule.class)
public interface NotesComponent {

    void inject(NotesActivity activity);

}
