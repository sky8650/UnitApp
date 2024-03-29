package com.xiaolei.unit.baselibrary.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.xiaolei.unit.baselibrary.R;
import com.xiaolei.unit.baselibrary.R2;
import com.xiaolei.unit.baselibrary.baseMvp.ICoreLoadingView;
import com.xiaolei.unit.baselibrary.baseMvp.ICorePresenter;
import com.xiaolei.unit.baselibrary.view.MyPullToRefreshListener;
import com.xiaolei.unit.baselibrary.view.ProgressLayout;
import com.xiaolei.unit.baselibrary.view.SuperSwipeRefreshLayout;

import butterknife.BindView;

/**
 * 简单显示一个列表的Activity
 * 子类需实现相关的抽象方法和编写adapter
 */

public abstract class BaseRecyclerViewActivity<T extends ICorePresenter> extends BaseActivity implements ICoreLoadingView {


    @BindView(R2.id.rl_back)
    protected RelativeLayout rlBack;
    @BindView(R2.id.tv_title)
    protected TextView tvTitle;
    @BindView(R2.id.rv_base_recyclerView)
    protected RecyclerView rvBaseRecyclerView;
    @BindView(R2.id.swipe)
    protected SuperSwipeRefreshLayout swipe;
    @BindView(R2.id.progressLayout)
    protected ProgressLayout progressLayout;
    protected MyPullToRefreshListener pullToRefreshListener;

    @Override
    protected int getLayout() {
        return R.layout.activity_base_recyclerview;
    }

    @Override
    protected ICorePresenter getPresenter() {
        return getMPresenter();
    }

    protected abstract T getMPresenter();

    @Override
    protected void initEventAndData() {
        tvTitle.setText(getTitleText()==null?"":getTitleText());
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pullToRefreshListener = new MyPullToRefreshListener(mContext, swipe);
        pullToRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                setPullToRefresh();
            }
        });
        swipe.setOnPullRefreshListener(pullToRefreshListener);
        rvBaseRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    /**
     * 下拉刷新
     */
    protected abstract void setPullToRefresh();

    /**
     * @return 标题
     */
    protected abstract String getTitleText();

    /**
     * 失败重试
     */
    protected abstract void onErrorResetData();

    /**
     * 显示Loading
     */
    @Override
    public void showLoading() {
        if (!progressLayout.isContent()) {
            progressLayout.showLoading();
        }
    }

    /**
     * 显示内容
     */
    @Override
    public void showContent() {
        pullToRefreshListener.refreshDone();
        if (!progressLayout.isContent()) {
            progressLayout.showContent();
        }
    }

    /**
     * 错误点击重试
     * @param errorMsg 错误信息
     */
    @Override
    public void showError(String errorMsg) {
        Logger.e(errorMsg);
        pullToRefreshListener.refreshDone();
        progressLayout.showError(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onErrorResetData();
            }
        });
    }


}
