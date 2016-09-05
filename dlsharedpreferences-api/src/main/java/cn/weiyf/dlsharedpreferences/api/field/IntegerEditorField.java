package cn.weiyf.dlsharedpreferences.api.field;


import cn.weiyf.dlsharedpreferences.api.EditorHelper;
import cn.weiyf.dlsharedpreferences.api.field.base.BaseEditorField;

/**
 * Created by weiyf on 2016/7/4.
 */

public class IntegerEditorField<E extends EditorHelper> extends BaseEditorField<Integer, E> {

    public IntegerEditorField(E mEditorHelper, String mKey) {
        super(mEditorHelper, mKey);
    }

    @Override
    public E put(Integer value) {
        mEditorHelper.getEditor().putInt(mKey, value);
        return mEditorHelper;
    }
}
