package jelletenbrinke.com.mvp.notes;

import java.util.List;

import javax.inject.Inject;

import jelletenbrinke.com.mvp.data.Note;
import jelletenbrinke.com.mvp.data.NotesRepository;
import jelletenbrinke.com.mvp.utils.schedulers.BaseSchedulerProvider;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jelle on 11/01/2017.
 */

public class NotesPresenter implements NotesContract.Presenter {

    private NotesContract.View notesView;

    @Inject
    NotesRepository notesRepository;

    @Inject
    BaseSchedulerProvider schedulerProvider;

    private CompositeSubscription subscriptions;

    @Inject
    NotesPresenter(NotesContract.View notesView) {
        this.notesView = notesView;
//        this.notesRepository = notesRepository;
//        this.schedulerProvider = schedulerProvider;
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void getNotes() {
        Subscription subscription = notesRepository
                .getNotes()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(new Observer<List<Note>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        notesView.showLoadingNotesError();
                    }

                    @Override
                    public void onNext(List<Note> notes) {
                        notesView.showNotes(notes);
                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public void pause() {
        subscriptions.unsubscribe();
    }

    @Override
    public void resume() {

    }
}
