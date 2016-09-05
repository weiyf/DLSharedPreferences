package cn.weiyf.dlsharedpreferences.api.field;


import android.content.SharedPreferences;

import cn.weiyf.dlsharedpreferences.api.field.base.BaseSprefField;

/**
 * Created by weiyf on 2016/7/4.
 */

public class FloatSprefField extends BaseSprefField<Float> {

    public FloatSprefField(SharedPreferences mSharedPreferences, String mKey, Float defaultValue) {
        super(mSharedPreferences, mKey, defaultValue);
    }

    @Override
    public Float get(Float defaultVaule) {
        if (defaultVaule == null) {
            defaultVaule = 0F;
        }
        return mSharedPreferences.getFloat(mKey, defaultVaule);
    }

    @Override
    public void putInternal(Float value) {
        apply(edit().putFloat(mKey, value));
    }
}
