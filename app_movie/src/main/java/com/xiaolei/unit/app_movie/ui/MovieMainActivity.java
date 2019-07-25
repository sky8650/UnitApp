package com.xiaolei.unit.app_movie.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaolei.unit.app_movie.R;
import com.xiaolei.unit.app_movie.R2;
import com.xiaolei.unit.baselibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieMainActivity extends BaseActivity {
    @BindView(R2.id.lib_tv)
    TextView  lib_tv;


    @Override
    protected int getLayout() {
        return R.layout.activity_movie_main;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        super.initEventAndData(savedInstanceState);
        ButterKnife.bind(this);
    }


    @OnClick({R2.id.lib_tv})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.lib_tv) {
            Toast.makeText(MovieMainActivity.this, "点击TextView", Toast.LENGTH_SHORT).show();
        }
    }




}
