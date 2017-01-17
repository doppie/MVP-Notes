package jelletenbrinke.com.mvp.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jelletenbrinke.com.mvp.data.local.NotesLocalDataSource;

/**
 * Created by jelle on 16/01/2017.
 */
@Module
public class NotesRepositoryModule {

    @Singleton
    @Provides
    NotesDataSource provideDataSource() {
        return NotesLocalDataSource.getInstance();
    }
}
