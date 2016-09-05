package cn.weiyf.dlsharedpreferences.api;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;


/**
 * Created by weiyf on 2016/7/4.
 */

public class SprefHelper {

    protected final SharedPreferences mSharedPreferences;

    public SprefHelper(Context context, String suffixName) {
        this.mSharedPreferences = context.getSharedPreferences(getSpfName() + suffixName, 0);
    }

    private String getSpfName() {
        return "MySpref";
    }

    public final SharedPreferences.Editor getEditor() {
        return mSharedPreferences.edit();
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public final void clear() {
        mSharedPreferences.edit().clear().apply();
    }

}
