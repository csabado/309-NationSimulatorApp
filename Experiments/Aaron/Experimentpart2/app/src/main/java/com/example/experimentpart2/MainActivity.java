package com.example.experimentpart2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button bigButton;
    private EditText userName;
    private EditText passWord;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userName = (EditText)findViewById(R.id.userNam);
        passWord = (EditText)findViewById(R.id.passWord);
        bigButton = (Button) findViewById(R.id.bigButton);
        bigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userName.getText().toString().equals("admin")&&passWord.getText().toString().equals("password")) {
                    openActivity2();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"wrong!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
        public void openActivity2()
        {
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);


        }


    }




