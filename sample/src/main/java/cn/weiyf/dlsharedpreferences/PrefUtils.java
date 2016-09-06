package cn.weiyf.dlsharedpreferences;

import cn.weiyf.dlsharedpreferences.api.Spref;
import cn.weiyf.dlsharedpreferences.api.field.DefaultBoolean;
import cn.weiyf.dlsharedpreferences.api.field.DefaultLong;

/**
 * Created by Administrator on 2016/9/5.
 */
@Spref
public class PrefUtils {

    @DefaultBoolean(true)
    boolean isLogin;

    @DefaultLong(1L)
    long time;
}
