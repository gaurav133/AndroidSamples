package com.android.sample.fragmentsample;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "MainActivity : onCreate");
        }
        // Load MainActivityFragment.
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(android.R.id.content, new MainActivityFragment(), "dd");
        transaction.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "MainActivity : onStart");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "MainActivity : onPause");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "MainActivity : onStop");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "MainActivity : onRestart");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "MainActivity : onDestroy");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "MainActivity : onSaveInstanceState");
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "MainActivity : onRestoreInstanceState");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonClick(View v) {

        // Load fragment two on button click.
     /*   FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(android.R.id.content, new FragmentTwo());
        transaction.commit();
*/
        // Show an alert dialog to observe callbacks order.
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hello");
        AlertDialog dialog = builder.create();
        dialog.show();*/

        Intent dialogIntent = new Intent();
        dialogIntent.setComponent(new ComponentName("com.android.sample.fragmentsample", "com.android.sample.fragmentsample.DialogActivity"));
        startActivity(dialogIntent);
    }
/*
    private String getCurrentVisibleFragment() {

    }*/

    @Override
    protected void onResume() {
        super.onResume();

        String currentFragment = getFragmentManager().findFragmentById(android.R.id.content).toString();

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "MainActivity : onResume");
            Log.d(LOG_TAG, "Currently displayed fragment is : " + currentFragment);
        }
    }


}
