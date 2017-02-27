package com.toddashmore.weathertime.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.toddashmore.weathertime.utilities.FragmentHelper;
import com.toddashmore.weathertime.utilities.SharedPreferencesHelper;
import edu.unmc.weathertime.R;

/**
 * This DialogFragment is displayed when the user has not yet selected a location for weather data to be displayed.
 */
public class AddCityDialogFragment extends DialogFragment {
    Context context;

    public static AddCityDialogFragment newInstance() {
        Bundle args = new Bundle();

        AddCityDialogFragment fragment = new AddCityDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please set a location").setMessage("Example: Paris, France or 95864");

        // Set up the input
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("ADD CITY",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String location = input.getText().toString();
                SharedPreferencesHelper.CreateCityInSharedPrefs(getActivity(), location);
                FragmentHelper.pushToFragmentManager(getFragmentManager(), R.id.content_frame, new ForecastMasterFragment(), false);
            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = getActivity();
    }

    public boolean isActive() {
        return isAdded() && !isDetached() && !isRemoving();
    }
}
