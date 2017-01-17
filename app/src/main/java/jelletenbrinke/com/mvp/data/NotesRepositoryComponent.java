package jelletenbrinke.com.mvp.data;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jelle on 16/01/2017.
 */

@Singleton
@Component(modules = NotesRepositoryModule.class)
public interface NotesRepositoryComponent {

    NotesRepository getNotesRepository();

}
