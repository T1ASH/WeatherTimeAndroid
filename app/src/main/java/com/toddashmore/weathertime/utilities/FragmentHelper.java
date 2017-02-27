package com.toddashmore.weathertime.utilities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class FragmentHelper {
    /**
     * Pushes a new fragment on to the fragment manager stack.
     *
     * @param fragmentManager Pass in the FragmentManager so that it can do the necessary work.
     * @param target Id of the content frame to be targeted.
     * @param fragment Fragment to be pushed onto the stack.
     * @param addToBackStack Does this needed to be added to the back stack for navigation purposes?
     */
    public static void pushToFragmentManager(FragmentManager fragmentManager, int target, Fragment fragment, Boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(target, fragment);

        if(addToBackStack) transaction.addToBackStack(null);

        transaction.commit();
    }
}
