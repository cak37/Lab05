package com.example.cak37.lab05;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/* Lab 05
 *
 * @author: Carol Schott w/ Jake Schott & Jay Bidelow
 * @version: 1.0 (October 2016)
 */

// Main class
public class Lab05 extends AppCompatActivity {

    private SharedPreferences prefs;
    private Boolean rememberPreference = false;


    // Override onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab05);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        showPreferences();
    }

    private void showPreferences() {
        TextView preferenceTextView = (TextView) findViewById(R.id.display);
        preferenceTextView.setText("Preference: " + prefs.getBoolean("pref_remember_boolean", false));
    }

    // Override onCreateOptionsMenu method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("pref_remember_boolean", rememberPreference);
        editor.commit();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        rememberPreference = prefs.getBoolean("pref_remember_boolean", false);
        showPreferences();
    }

    // Override onOptionsItemSelected method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            // Go to other activity if selected
            case R.id.goToAbout:
                startActivity(new Intent(Lab05.this, lab05settings.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}