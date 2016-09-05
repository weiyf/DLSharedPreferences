package cn.weiyf.dlsharedpreferences.api;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;

/**
 * Created by weiyf on 2016/7/4.
 */

public class EditorHelper {

    private final SharedPreferences.Editor mEditor;

    public EditorHelper(SharedPreferences.Editor mEditor) {
        this.mEditor = mEditor;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public final void clear() {
        mEditor.clear().apply();
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public final void apply() {
        mEditor.apply();
    }

    public final boolean commit() {
        return mEditor.commit();
    }

    public SharedPreferences.Editor getEditor() {
        return mEditor;
    }
}
