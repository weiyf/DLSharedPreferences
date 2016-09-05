package cn.weiyf.dlsharedpreferences.api.field.base;


import cn.weiyf.dlsharedpreferences.api.EditorHelper;

/**
 * Created by weiyf on 2016/7/4.
 */

public abstract class BaseEditorField<T, E extends EditorHelper> {

    protected final E mEditorHelper;
    protected final String mKey;

    public BaseEditorField(E mEditorHelper, String mKey) {
        this.mEditorHelper = mEditorHelper;
        this.mKey = mKey;
    }

    public final E remove() {
        mEditorHelper.getEditor().remove(mKey);
        return mEditorHelper;
    }

    public abstract E put(T value);
}
