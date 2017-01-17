package jelletenbrinke.com.mvp.notes.dagger;

import dagger.Module;
import dagger.Provides;
import jelletenbrinke.com.mvp.data.NotesRepository;
import jelletenbrinke.com.mvp.data.local.NotesLocalDataSource;
import jelletenbrinke.com.mvp.notes.NotesContract;
import jelletenbrinke.com.mvp.utils.schedulers.BaseSchedulerProvider;
import jelletenbrinke.com.mvp.utils.schedulers.SchedulerProvider;

/**
 * Created by jelle on 16/01/2017.
 */
@Module
public class NotesPresenterModule {

    private final NotesContract.View view;

    public NotesPresenterModule(NotesContract.View view) {
        this.view = view;
    }

    @Provides
    NotesContract.View provideNotesView() {
        return view;
    }

    @Provides
    NotesRepository providesNotesRepository(NotesLocalDataSource dataSource) {
        return NotesRepository.getInstance(dataSource);
    }

    @Provides
    NotesLocalDataSource providesNotesLocalDataSource() {
        return NotesLocalDataSource.getInstance();
    }

    @Provides
    BaseSchedulerProvider providesSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
