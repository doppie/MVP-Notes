package jelletenbrinke.com.mvp.notes

import javax.inject.Inject

import jelletenbrinke.com.mvp.data.Note
import jelletenbrinke.com.mvp.data.NotesRepository
import jelletenbrinke.com.mvp.utils.schedulers.BaseSchedulerProvider
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by jelle on 11/01/2017.
 */

class NotesPresenter @Inject
internal constructor(private val notesView: NotesContract.View, internal var notesRepository: NotesRepository,
                     internal var schedulerProvider: BaseSchedulerProvider) : NotesContract.Presenter {

    private val subscriptions: CompositeSubscription = CompositeSubscription()

    override fun getNotes() {
        val subscription = notesRepository
                .notes
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        { notes -> notesView.showNotes(notes) },
                        { error -> notesView.showLoadingNotesError() }
                )

        subscriptions.add(subscription)
    }

    override fun pause() {
        subscriptions.unsubscribe()
    }

    override fun resume() {

    }
}
