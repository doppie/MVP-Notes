package jelletenbrinke.com.mvp.utils

import android.view.View

/**
 * Created by jelle on 11/01/2017.
 */

interface ClickListener {

    fun onClick(v: View, position: Int, isLongClick: Boolean)

}
