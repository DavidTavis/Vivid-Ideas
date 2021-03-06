package layout.settings;

import android.content.Context;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

import com.app.david.mywidget.R;

import layout.utils.TraceUtils;
import layout.utils.Utils;

/**
 * Created by TechnoA on 01.04.2017.
 */

public class SettingsFragment extends PreferenceFragment {
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        context = getActivity();
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        ListPreference timeUpdate = (ListPreference) findPreference("interval");
        String defaultValue = timeUpdate.getValue();
        if(defaultValue==null){
            timeUpdate.setValueIndex(5);
        }

        ListPreference fontSize = (ListPreference) findPreference("fontSize");
        String fontSizeValue = fontSize.getValue();
        if(fontSizeValue==null){
            fontSize.setValueIndex(3);
        }
    }

    @Override
    public void onResume() {

        TraceUtils.logInfo("SettingsFragment onResume");
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(Utils.getGlobal(context).getSettings());
    }


    @Override
    public void onPause() {
        TraceUtils.logInfo("SettingsFragment onPause");
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(Utils.getGlobal(context).getSettings());
        super.onPause();
    }
}
