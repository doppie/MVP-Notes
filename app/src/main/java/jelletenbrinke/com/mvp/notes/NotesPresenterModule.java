package jelletenbrinke.com.mvp.notes;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jelletenbrinke.com.mvp.data.NotesDataSource;
import jelletenbrinke.com.mvp.data.NotesRepository;
import jelletenbrinke.com.mvp.data.local.NotesLocalDataSource;
import jelletenbrinke.com.mvp.utils.schedulers.BaseSchedulerProvider;
import jelletenbrinke.com.mvp.utils.schedulers.SchedulerProvider;

/**
 * Created by jelle on 16/01/2017.
 */
@Module
public class NotesPresenterModule {

    private final NotesContract.View view;
    private final BaseSchedulerProvider scheduler;

    public NotesPresenterModule(NotesContract.View view, BaseSchedulerProvider scheduler) {
        this.view = view;
        this.scheduler = scheduler;
    }

    @Provides
    NotesContract.View provideNotesView() {
        return view;
    }

    @Provides
    BaseSchedulerProvider provideScheduler() {
        return scheduler;
    }
//
//    @Provides
//    @Singleton
//    NotesRepository providesNotesRepository(NotesLocalDataSource dataSource) {
//        return NotesRepository.getInstance(dataSource);
//    }
//
//    @Provides
//    @Singleton
//    NotesLocalDataSource providesNotesLocalDataSource() {
//        return NotesLocalDataSource.getInstance();
//    }
//
//    @Provides
//    @Singleton
//    BaseSchedulerProvider providesSchedulerProvider() {
//        return SchedulerProvider.getInstance();
//    }
}
