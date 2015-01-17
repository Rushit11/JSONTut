package com.example.jsontutorial;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jsontutorial.AsyncTask.BasicAsyncTask;
import com.example.jsontutorial.AsyncTask.OnAsyncResult;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void getData(View v){

        OnAsyncResult onAsyncResult = new OnAsyncResult() {
            @Override
            public void onAsynResult(String result) {
                Intent i = new Intent(MainActivity.this, DisplayActivity.class);
                i.putExtra("result", result);
                startActivity(i);
            }
        };

        BasicAsyncTask basicAsyncTask = new BasicAsyncTask(this, onAsyncResult, "http://www.whitehouse.gov/facts/json/all/all");
        basicAsyncTask.execute();

    }
}
