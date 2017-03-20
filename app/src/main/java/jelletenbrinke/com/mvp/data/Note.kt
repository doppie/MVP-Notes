package jelletenbrinke.com.mvp.data

import java.io.Serializable
import java.util.Calendar

/**
 * Created by jelle on 11/01/2017.
 */
class Note(val id: Long, val note: String, val date: Calendar) : Serializable
