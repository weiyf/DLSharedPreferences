package cn.weiyf.dlsharedpreferences.api.field;


import cn.weiyf.dlsharedpreferences.api.EditorHelper;
import cn.weiyf.dlsharedpreferences.api.field.base.BaseEditorField;

/**
 * Created by weiyf on 2016/7/4.
 */

public class FloatEditorField< E extends EditorHelper> extends BaseEditorField<Float, E> {
    public FloatEditorField(E mEditorHelper, String mKey) {
        super(mEditorHelper, mKey);
    }

    @Override
    public E put(Float value) {
        mEditorHelper.getEditor().putFloat(mKey, value);
        return mEditorHelper;
    }
}
