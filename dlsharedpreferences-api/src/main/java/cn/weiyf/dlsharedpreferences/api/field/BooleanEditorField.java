package cn.weiyf.dlsharedpreferences.api.field;


import cn.weiyf.dlsharedpreferences.api.EditorHelper;
import cn.weiyf.dlsharedpreferences.api.field.base.BaseEditorField;

/**
 * Created by weiyf on 2016/7/4.
 */

public class BooleanEditorField<E extends EditorHelper> extends BaseEditorField<Boolean, E> {

    public BooleanEditorField(E mEditorHelper, String mKey) {
        super(mEditorHelper, mKey);
    }

    @Override
    public E put(Boolean value) {
        mEditorHelper.getEditor().putBoolean(mKey, value);
        return mEditorHelper;
    }
}
