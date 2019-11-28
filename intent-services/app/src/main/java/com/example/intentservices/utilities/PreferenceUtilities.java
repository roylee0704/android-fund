package com.example.intentservices.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public final class PreferenceUtilities {
    public static final String KEY_WATER_COUNT = "water-count";
    public static final String KEY_CHARGING_REMINDER_COUNT = "charging-reminder-count";
    private static final int DEFAULT_COUNT = 0;

    public static int getWaterCount(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int glassesOfWater = prefs.getInt(KEY_WATER_COUNT, DEFAULT_COUNT);
        return glassesOfWater;
    }

    public static int getChargingReminderCount(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int chargingReminders = prefs.getInt(KEY_CHARGING_REMINDER_COUNT, DEFAULT_COUNT);
        return chargingReminders;
    }

    synchronized public static void incrementWaterCount(Context context) {
        int waterCount = PreferenceUtilities.getWaterCount(context);
        PreferenceUtilities.setWaterCount(context, ++waterCount);
    }

    synchronized private static void setWaterCount(Context context, int glassesOfWater) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Log.d("ROY", glassesOfWater + "");
        editor.putInt(KEY_WATER_COUNT, glassesOfWater);
        editor.apply();
    }

    synchronized public static void incrementChargingReminderCount(Context context) {
        int chargingReminders =  PreferenceUtilities.getChargingReminderCount(context);
        PreferenceUtilities.setChargingRemainderCount(context, ++chargingReminders);
    }

    synchronized private static void setChargingRemainderCount(Context context, int chargingReminders) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(KEY_CHARGING_REMINDER_COUNT, chargingReminders);
        editor.apply();
    }

}
