package jelletenbrinke.com.mvp.notes.dagger

import dagger.Component
import jelletenbrinke.com.mvp.notes.NotesActivity
import jelletenbrinke.com.mvp.notes.dagger.NotesPresenterModule

/**
 * Created by jelle on 16/01/2017.
 */
@Component(modules = arrayOf(NotesPresenterModule::class))
interface NotesComponent {

    fun inject(activity: NotesActivity)

}
