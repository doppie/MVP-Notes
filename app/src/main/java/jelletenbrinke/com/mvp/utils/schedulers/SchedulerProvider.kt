package jelletenbrinke.com.mvp.utils.schedulers

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Provides different types of schedulers.
 */
class SchedulerProvider// Prevent direct instantiation.
private constructor() : BaseSchedulerProvider {

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    companion object {

        private var theInstance: SchedulerProvider? = null

        @Synchronized fun getInstance(): SchedulerProvider {
            if (theInstance == null) {
                theInstance = SchedulerProvider()
            }
            return theInstance as SchedulerProvider
        }
    }
}