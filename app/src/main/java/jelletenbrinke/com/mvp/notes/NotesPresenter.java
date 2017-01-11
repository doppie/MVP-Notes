package jelletenbrinke.com.mvp.notes;

import java.util.List;

import jelletenbrinke.com.mvp.data.Note;
import jelletenbrinke.com.mvp.data.NotesRepository;
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
    private NotesRepository notesRepository;

    private CompositeSubscription subscriptions;


    public NotesPresenter(NotesContract.View notesView) {
        this.notesView = notesView;
        notesRepository = NotesRepository.getInstance();
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void getNotes() {
        Subscription subscription = notesRepository
                .getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Note>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

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
