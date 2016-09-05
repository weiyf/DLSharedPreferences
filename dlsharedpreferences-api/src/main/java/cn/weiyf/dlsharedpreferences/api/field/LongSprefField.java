package cn.weiyf.dlsharedpreferences.api.field;


import android.content.SharedPreferences;

import cn.weiyf.dlsharedpreferences.api.field.base.BaseSprefField;

/**
 * Created by weiyf on 2016/7/4.
 */

public class LongSprefField extends BaseSprefField<Long> {


    public LongSprefField(SharedPreferences mSharedPreferences, String mKey, Long defaultValue) {
        super(mSharedPreferences, mKey, defaultValue);
    }

    @Override
    public Long get(Long defaultVaule) {
        if (defaultVaule == null) {
            defaultVaule = 0L;
        }
        return mSharedPreferences.getLong(mKey, defaultVaule);
    }

    @Override
    public void putInternal(Long value) {
        apply(edit().putLong(mKey, value));
    }
}
