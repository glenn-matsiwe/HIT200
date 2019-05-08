package com.example.hitcompanion;

import com.parse.Parse;
import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("project")
                .clientKey("android")
                .server("http://10.42.0.1:1337/parse/")
                .build()

        );
    }
}

