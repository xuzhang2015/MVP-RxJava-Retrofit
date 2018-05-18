package com.example.xz.mytelegraphapp.view;

/**
 * @Description View layer in MVP
 */

public interface IView {
    /**
     * @description global show the loading
     */
    void showLoading();

    /**
     * @description global show the loading with msg
     */
    void showLoading(String msg);

    /**
     * @description global show the loading with msg and progress
     */
    void showLoading(String msg, int progress);

    /**
     * @description global hide the loading
     */

    void dismissLoading();

    /**
     * @description global show the message
     */
    void showMsg(String msg);

    /**
     * @description global close the UI
     */
    void close();

    /**
     * @description check the fragment is still available
     */
    boolean isActive();
}
