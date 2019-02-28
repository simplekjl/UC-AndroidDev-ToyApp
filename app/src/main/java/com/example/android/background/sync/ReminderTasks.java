package com.example.android.background.sync;


import android.content.Context;

import com.example.android.background.utilities.PreferenceUtilities;

public class ReminderTasks {


    public static String ACTION_INCREMENT_WATER_COUNT = "ACTION_INCREMENT_WATER_COUNT";

    public static void executeTask(Context context, String action) {

        if (action.equals(ACTION_INCREMENT_WATER_COUNT)) {
            incrementWaterCount(context);
        }
    }

    private static void incrementWaterCount(Context context) {
        PreferenceUtilities.incrementWaterCount(context);
    }
}
