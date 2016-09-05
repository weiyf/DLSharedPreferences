package cn.weiyf.dlsharedpreferences.api.field;


import cn.weiyf.dlsharedpreferences.api.EditorHelper;
import cn.weiyf.dlsharedpreferences.api.field.base.BaseEditorField;

/**
 * Created by weiyf on 2016/7/4.
 */

public class LongEditorField<E extends EditorHelper> extends BaseEditorField<Long, E> {

    public LongEditorField(E mEditorHelper, String mKey) {
        super(mEditorHelper, mKey);
    }

    @Override
    public E put(Long value) {
        mEditorHelper.getEditor().putLong(mKey, value);
        return mEditorHelper;
    }
}
