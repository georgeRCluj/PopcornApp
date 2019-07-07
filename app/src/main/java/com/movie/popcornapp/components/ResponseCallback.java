package com.movie.popcornapp.components;

import androidx.annotation.Nullable;

/**
 * @author george.radu on 2019-07-07.
 */
public interface ResponseCallback<T> {
    void response(Boolean success, @Nullable String message, @Nullable T data);
}
