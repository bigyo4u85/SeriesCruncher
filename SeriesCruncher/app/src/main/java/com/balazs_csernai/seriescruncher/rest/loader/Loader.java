package com.balazs_csernai.seriescruncher.rest.loader;

/**
 * Created by ErikKramli on 2016.01.15..
 */
public interface Loader {

    interface Callback<T> {
        void onSuccess(T result);
        void onFailure();
    }

    void bind();

    void unbind();
}
