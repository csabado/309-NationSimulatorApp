/**
* @author Chloe Sabado 
*/
package com.example.settingspage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
/**
* This class creates the Settings pages and controls  
*/
public class MainActivity extends AppCompatActivity {
    /**
    * These params help control the switches and allow for a log out
    */
    private Button btnLogout;
    private ImageButton btnTut;
    private Switch swSound, swDisplay, swNotifications, swAnimation;
    /**
    * This creates the activitys and the functionality of the switches/buttons
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
        * Assigns on screen swtiches to params 
        */
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnTut = (ImageButton) findViewById(R.id.btntut);
        swSound = (Switch) findViewById(R.id.sound);
        swDisplay = (Switch) findViewById(R.id.dmSwitch);
        swNotifications = (Switch) findViewById(R.id.notifSwitch);
        swAnimation = (Switch) findViewById(R.id.animationswitch);

        /**
        * Checks the display switch, if true start dark mode, if false turn night mode off 
        */
        if(swDisplay.isChecked() == true){
            AppCompatDelegate
                    .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate
                    .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        /**
        * Checks the notification switch, if true notifications will appear, if off no notifications will display 
        */
        if(swNotifications.isChecked() == true){

        }
        /**
        * Checks animation switch, if true page animations will be on, if false no animations between pages
        */
        if(swAnimation.isChecked() == true){

        }
        /**
        * Assigns button functionality to log out, when clicked user is logged out and sent to login screen
        */
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });
        /**
        * Assigns buttons to open up the tutorial screen 
        */
        btnTut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });

    /**
    * This methods helps create a new open activity, so that it starts the invoked screen 
    */
    }
    private void openActivity1(){
        Intent intent = new Intent(this, TutorialScreen.class);
        startActivity(intent);
    }


}
