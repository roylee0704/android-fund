package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

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

    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";


    private TextView mLifecycleDisplay;


    private static final ArrayList<String> mLifecycleCallBacks = new ArrayList<>();


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

        if (savedInstanceState != null && savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
            String allPreviousLifecycleCallbacks = savedInstanceState.getString(LIFECYCLE_CALLBACKS_TEXT_KEY);
            mLifecycleDisplay.setText(allPreviousLifecycleCallbacks);
        }

        for(int i = mLifecycleCallBacks.size()- 1; i>=0;i--) {
            mLifecycleDisplay.append(mLifecycleCallBacks.get(i) + "\n");
        }

        mLifecycleCallBacks.clear();

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

    @Override
    protected void onResume() {
        super.onResume();
        logAndAppend(ON_RESUME);
    }

    // COMPLETED (4) Override onPause, call super.onPause, and call logAndAppend with ON_PAUSE
    /**
     * Called when the system is about to start resuming a previous activity. This is typically
     * used to commit unsaved changes to persistent data, stop animations and other things that may
     * be consuming CPU, etc. Implementations of this method must be very quick because the next
     * activity will not be resumed until this method returns.
     *
     * Followed by either onResume() if the activity returns back to the front, or onStop() if it
     * becomes invisible to the user.
     */
    @Override
    protected void onPause() {
        super.onPause();
        logAndAppend(ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mLifecycleCallBacks.add(0, ON_STOP);
        logAndAppend(ON_STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLifecycleCallBacks.add(0, ON_DESTROY);
        logAndAppend(ON_DESTROY);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndAppend(ON_SAVE_INSTANCE_STATE);

        String lifecycleDisplayTextViewContents = mLifecycleDisplay.getText().toString();
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, lifecycleDisplayTextViewContents);

    }

    private void logAndAppend(String lifecycleEvent){
        Log.d(TAG, "Lifecycle Event: "+ lifecycleEvent);

        mLifecycleDisplay.append(lifecycleEvent + "\n");
    }

    public void resetLifecycleDisplay(View view) {
        mLifecycleDisplay.setText("Lifecycle callbacks:\n");
    }

}
