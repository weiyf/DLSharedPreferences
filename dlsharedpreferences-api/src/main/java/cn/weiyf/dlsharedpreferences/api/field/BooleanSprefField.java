package cn.weiyf.dlsharedpreferences.api.field;


import android.content.SharedPreferences;

import cn.weiyf.dlsharedpreferences.api.field.base.BaseSprefField;

/**
 * Created by weiyf on 2016/7/4.
 */

public class BooleanSprefField extends BaseSprefField<Boolean> {


    public BooleanSprefField(SharedPreferences mSharedPreferences, String mKey, Boolean defaultValue) {
        super(mSharedPreferences, mKey, defaultValue);
    }

    @Override
    public Boolean get(Boolean defaultVaule) {
        if (defaultVaule == null) {
            defaultVaule = false;
        }
        return mSharedPreferences.getBoolean(mKey, defaultVaule);
    }

    @Override
    protected void putInternal(Boolean value) {
        apply(edit().putBoolean(mKey, value));
    }
}
