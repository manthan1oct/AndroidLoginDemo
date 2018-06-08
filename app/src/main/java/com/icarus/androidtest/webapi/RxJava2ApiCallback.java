package com.icarus.androidtest.webapi;

public interface RxJava2ApiCallback<T> {
    void onSuccess(T t);

    void onFailed(Throwable throwable);
}
