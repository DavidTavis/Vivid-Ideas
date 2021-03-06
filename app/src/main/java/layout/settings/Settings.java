package layout.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import layout.utils.TraceUtils;

/**
 * Created by Angelo W on 19.04.2017.
 */

public class Settings implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String PREF_NAME = "com.example.david.PREFERENCE_FILE_KEY";
    private static final String QUOTE_ID = "quote_id",
                                QUOTE_TEXT = "quote_text",
                                RINGTONE = "ringtone",
                                USE_SOUND = "pref_sound_use",
                                INTERVAL = "interval",
                                FONT_SIZE = "fontSize";

    private Context context;
    private SharedPreferences sharedPref;
    private SettingsChangedListener settingsChangedListener;

    public Settings(Context context) {

        this.context = context;
        this.sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setSettingsChangedListener(SettingsChangedListener settingsChangedListener) {

        this.settingsChangedListener = settingsChangedListener;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if(settingsChangedListener != null)
            settingsChangedListener.onSettingsChanged(key, context);
        TraceUtils.logInfo("onSharedPreferenceChanged key = " + key);
    }

    public String getQuote() {

        return sharedPref.getString(QUOTE_TEXT, "");
    }

    public void setQuote(String quote) {

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(QUOTE_TEXT, quote);
        editor.commit();
    }

    public long getQuoteId() {
        return sharedPref.getLong(QUOTE_ID, -1);
    }

    public void setQuoteId(long id) {

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(QUOTE_ID, id);
        editor.commit();
    }

    public String getRingtone() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString(RINGTONE, "default ringtone");
    }

    public boolean getUseSound() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getBoolean(USE_SOUND,true);
    }

    public String getInterval() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString(INTERVAL, "60");
    }

    public String getFontSize() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString(FONT_SIZE, "13");
    }

    public void close()  {

        TraceUtils.logInfo("Settings deleteTitlePref");
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREF_NAME, 0).edit();
        prefs.remove(QUOTE_ID);
        prefs.remove(QUOTE_TEXT);
        prefs.remove(RINGTONE);
        prefs.remove(USE_SOUND);
        prefs.apply();
    }

}
