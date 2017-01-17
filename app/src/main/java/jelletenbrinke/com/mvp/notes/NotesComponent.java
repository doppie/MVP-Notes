package jelletenbrinke.com.mvp.notes;

import javax.inject.Scope;

import dagger.Component;
import jelletenbrinke.com.mvp.data.NotesRepositoryComponent;

/**
 * Created by jelle on 16/01/2017.
 */
@NotesActivityScope
@Component(dependencies = NotesRepositoryComponent.class, modules = NotesPresenterModule.class)
public interface NotesComponent {

    void inject(NotesActivity activity);

}
