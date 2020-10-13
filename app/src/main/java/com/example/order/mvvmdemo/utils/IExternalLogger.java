package com.example.order.mvvmdemo.utils;

/**
 * Created by hzy, 2018/12/5
 * 提供外部日志导出工具，供线上调试包接入用
 */
public interface IExternalLogger {

    /**
     * VERBOSE级别信息打印
     * @param tag 打印的标签
     * @param msg 打印内容
     */
    void v(String tag, String msg);

    /**
     * DEBUG级别信息打印
     * @param tag 打印的标签
     * @param msg 打印内容
     */
    void d(String tag, String msg);

    /**
     * INFO级别信息打印
     * @param tag 打印的标签
     * @param msg 打印内容
     */
    void i(String tag, String msg);

    /**
     * WARNING级别信息打印
     * @param tag 打印的标签
     * @param msg 打印内容
     */
    void w(String tag, String msg);

    /**
     * ERROR级别信息打印
     * @param tag 打印的标签
     * @param msg 打印内容
     */
    void e(String tag, String msg);
}
