package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";


    private TextView mLifecycleDisplay;


    /**
     * Called when the activity is first created. This is where you should do all of your normal
     * static set up: create views, bind data to lists, etc.
     *
     * Always followed by onStart().
     *
     * @param savedInstanceState The Activity's previously frozen state, if there was one.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLifecycleDisplay = (TextView) findViewById(R.id.tv_lifecycle_events_display);

        logAndAppend(ON_CREATE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logAndAppend(ON_RESTART);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndAppend(ON_START);
    }


    protected void onResume() {
        super.onResume();
        logAndAppend(ON_RESUME);
    }

    protected void onPause() {
        super.onPause();
        logAndAppend(ON_PAUSE);
    }

    protected void onStop() {
        super.onStop();
        logAndAppend(ON_STOP);
    }


    protected void OnDestroy() {
        super.onDestroy();
        logAndAppend(ON_DESTROY);
    }

    private void logAndAppend(String lifecycleEvent){
        Log.d(TAG, "Lifecycle Event: "+ lifecycleEvent);

        mLifecycleDisplay.append(lifecycleEvent + "\n");
    }

    public void resetLifecycleDisplay(View view) {
        mLifecycleDisplay.setText("Lifecycle callbacks:\n");
    }

}
