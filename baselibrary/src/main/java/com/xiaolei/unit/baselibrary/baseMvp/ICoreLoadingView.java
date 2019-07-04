package com.xiaolei.unit.baselibrary.baseMvp;

/**
 * 默认的加载接口
 */

public interface ICoreLoadingView {
    void showLoading();
    void showContent();
    void showError(String errorMsg);


}
