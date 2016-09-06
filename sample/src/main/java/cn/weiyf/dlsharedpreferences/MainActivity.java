package cn.weiyf.dlsharedpreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PrefUtils_ prefUtils_ = PrefUtils_.create(this);
        Boolean aBoolean = prefUtils_.isLogin().get(false);
    }
}
