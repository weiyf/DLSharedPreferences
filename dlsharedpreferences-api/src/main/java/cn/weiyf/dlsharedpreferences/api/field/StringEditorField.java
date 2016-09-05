package cn.weiyf.dlsharedpreferences.api.field;


import cn.weiyf.dlsharedpreferences.api.EditorHelper;
import cn.weiyf.dlsharedpreferences.api.field.base.BaseEditorField;

/**
 * Created by weiyf on 2016/7/4.
 */

public class StringEditorField<E extends EditorHelper> extends BaseEditorField<String, E> {

    public StringEditorField(E mEditorHelper, String mKey) {
        super(mEditorHelper, mKey);
    }

    @Override
    public E put(String value) {
        mEditorHelper.getEditor().putString(mKey, value);
        return mEditorHelper;
    }
}
