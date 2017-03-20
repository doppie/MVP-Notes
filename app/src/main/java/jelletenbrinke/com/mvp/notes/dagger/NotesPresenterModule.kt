package jelletenbrinke.com.mvp.notes.dagger

import dagger.Module
import dagger.Provides
import jelletenbrinke.com.mvp.data.NotesRepository
import jelletenbrinke.com.mvp.data.local.NotesLocalDataSource
import jelletenbrinke.com.mvp.notes.NotesContract
import jelletenbrinke.com.mvp.utils.schedulers.BaseSchedulerProvider
import jelletenbrinke.com.mvp.utils.schedulers.SchedulerProvider

/**
 * Created by jelle on 16/01/2017.
 */
@Module
class NotesPresenterModule(private val view: NotesContract.View) {

    @Provides
    internal fun provideNotesView(): NotesContract.View {
        return view
    }

    @Provides
    internal fun providesNotesRepository(dataSource: NotesLocalDataSource): NotesRepository {
        return NotesRepository.getInstance(dataSource)
    }

    @Provides
    internal fun providesNotesLocalDataSource(): NotesLocalDataSource {
        return NotesLocalDataSource.getInstance()
    }

    @Provides
    internal fun providesSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider.getInstance()
    }
}
