package jelletenbrinke.com.mvp.notes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by jelle on 16/01/2017.
 */
@Scope
@Documented
@Retention(value= RetentionPolicy.RUNTIME)
public @interface NotesActivityScope {

}
