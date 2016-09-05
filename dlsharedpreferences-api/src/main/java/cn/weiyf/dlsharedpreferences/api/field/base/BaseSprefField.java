package cn.weiyf.dlsharedpreferences.api.field.base;


import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;

/**
 * Created by weiyf on 2016/7/4.
 */

public abstract class BaseSprefField<T> {
    protected final SharedPreferences mSharedPreferences;
    protected final String mKey;
    protected final T mDefaultValue;

    public BaseSprefField(SharedPreferences mSharedPreferences, String mKey, T defaultValue) {
        this.mSharedPreferences = mSharedPreferences;
        this.mKey = mKey;
        this.mDefaultValue = defaultValue;
    }

    public final boolean isExists() {
        return mSharedPreferences.contains(mKey);
    }

    public final void remove() {
        apply(edit().remove(mKey));
    }

    public final String getKey() {
        return mKey;
    }

    public final T get() {
        return get(mDefaultValue);
    }
    public abstract T get(T defaultVaule);

    public final void put(T value){
        putInternal((value == null) ? mDefaultValue : value);
    }

    protected abstract void putInternal(T value);

    protected SharedPreferences.Editor edit() {
        return mSharedPreferences.edit();
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    protected final void apply(SharedPreferences.Editor editor) {
        editor.apply();
    }
}
