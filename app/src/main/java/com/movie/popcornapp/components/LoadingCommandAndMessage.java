package com.movie.popcornapp.components;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author george.radu on 2019-07-08.
 */
public class LoadingCommandAndMessage {

    //region Properties

    /**
     * Loading Command enum
     */
    public enum LoadingCommand {
        show,
        hide
    }

    public @NonNull
    LoadingCommand command;

    public @Nullable
    String message;
    //endregion;

    //region Lifecycle
    public LoadingCommandAndMessage(@NonNull LoadingCommand command, @Nullable String message) {
        this.command = command;
        this.message = message;
    }
    //endregion;

}
