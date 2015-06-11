package com.example.saisandeep.navigationdrawer;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MyActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout dl;
    private ListView lv;
    String[] heroez;
    ActionBarDrawerToggle drawerListener;//used instead of implementing drawerListener interface

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        heroez=getResources().getStringArray(R.array.Heroes);
        dl= (DrawerLayout) findViewById(R.id.drawerLayout);
        lv= (ListView) findViewById(R.id.drawerList);
        drawerListener=new ActionBarDrawerToggle(this,dl,R.drawable.ic_launcher,R.string.open,R.string.close){

            @Override
            public void onDrawerClosed(View drawerView) {
               // super.onDrawerClosed(drawerView);
                Toast.makeText(MyActivity.this,"Drawer was closed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MyActivity.this,"Drawer was opened",Toast.LENGTH_SHORT).show();
            }

        };
        dl.setDrawerListener(drawerListener);
        getSupportActionBar().setHomeButtonEnabled(true);//used to enable the home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,heroez);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (drawerListener.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, heroez[i]+"was selected",Toast.LENGTH_SHORT).show();
        selectItem(i);
        //this selectItem and selectTitle are methods defined by us to set title to the name selected in the navig drawer
    }

    public void selectItem(int position )
    {

        lv.setItemChecked(position,true);
        selectTitle(heroez[position]);
    }

    public void selectTitle(String title)
    {
        getSupportActionBar().setTitle(title);
    }
}
