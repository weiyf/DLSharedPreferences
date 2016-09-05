package cn.weiyf.dlsharedpreferences.api.field;


import android.content.SharedPreferences;

import cn.weiyf.dlsharedpreferences.api.field.base.BaseSprefField;

/**
 * Created by weiyf on 2016/7/4.
 */

public class IntegerSperfField extends BaseSprefField<Integer> {


    public IntegerSperfField(SharedPreferences mSharedPreferences, String mKey, Integer defaultValue) {
        super(mSharedPreferences, mKey, defaultValue);
    }

    @Override
    public Integer get(Integer defaultVaule) {
        if (defaultVaule == null) {
            defaultVaule = 0;
        }
        return mSharedPreferences.getInt(mKey, defaultVaule);
    }

    @Override
    public void putInternal(Integer value) {
        apply(edit().putInt(mKey, value));
    }
}
