package com.toddashmore.weathertime.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.unmc.weathertime.R;
import com.toddashmore.weathertime.fragments.ForecastMasterFragment;

import static com.toddashmore.weathertime.utilities.FragmentHelper.pushToFragmentManager;


/**
 *  This is the main activity that is called by default by our app. It's only function is to act as a content frame for our fragment manager.
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This app doesn't use the action bar, so it is being hidden.
        getSupportActionBar().hide();

        // Setup the content view.
        setContentView(R.layout.activity_home);

        // Since this is just a blank activity, we must push ForecastMasterFragment onto the content_frame.
        pushToFragmentManager(getFragmentManager(), R.id.content_frame, new ForecastMasterFragment(), false);
    }
}
