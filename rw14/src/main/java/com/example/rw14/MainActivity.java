package com.example.rw14;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb=findViewById(R.id.tb_174);
        setSupportActionBar(tb);
        Toolbar toolbar=((Toolbar)findViewById(R.id.tb_174x1));
        FloatingActionButton fab=((FloatingActionButton)findViewById(R.id.fab));
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Snackbar.make(v,"交互行为",Snackbar.LENGTH_INDEFINITE).setAction("Undo",new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"悬浮按钮",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.back:
                Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.set:
                Toast.makeText(this, "set", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
