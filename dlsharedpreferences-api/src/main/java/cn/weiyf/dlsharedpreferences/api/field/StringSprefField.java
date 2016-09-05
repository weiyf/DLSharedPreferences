package cn.weiyf.dlsharedpreferences.api.field;


import android.content.SharedPreferences;

import cn.weiyf.dlsharedpreferences.api.field.base.BaseSprefField;

/**
 * Created by weiyf on 2016/7/4.
 */

public class StringSprefField extends BaseSprefField<String> {


    public StringSprefField(SharedPreferences mSharedPreferences, String mKey, String defaultValue) {
        super(mSharedPreferences, mKey, defaultValue);
    }

    @Override
    public String get(String defaultVaule) {
        if (defaultVaule == null) {
            defaultVaule = "";
        }
        return mSharedPreferences.getString(mKey, defaultVaule);
    }

    @Override
    public void putInternal(String value) {
        apply(edit().putString(mKey, value));
    }
}
