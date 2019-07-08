package com.movie.popcornapp.infrastructure;

import android.app.Application;
import android.content.Context;

/**
 * @author george.radu on 2019-07-08.
 */
public class App extends Application {

    private static Context _context;

    @Override
    protected void attachBaseContext(Context base) {
        _context = base;
        super.attachBaseContext(_context);
    }

    public static Context getContext() {
        return _context;
    }
}
