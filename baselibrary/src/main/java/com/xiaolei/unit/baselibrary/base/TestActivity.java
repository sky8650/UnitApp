package com.xiaolei.unit.baselibrary.base;

import android.os.Bundle;
import android.widget.TextView;

import com.xiaolei.unit.baselibrary.R;
import com.xiaolei.unit.baselibrary.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends BaseActivity {

    @BindView(R2.id.tv_test)
    TextView tvTest;

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        tvTest.setText("aaaaaa");

    }
}
