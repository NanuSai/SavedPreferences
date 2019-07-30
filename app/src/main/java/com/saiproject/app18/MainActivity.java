package com.saiproject.app18;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         checkBox = findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked){

            checkBox.setText("The Check is checked");
        }

        else{
            checkBox.setText("Not checked!");
        }
    }


    //When onPause method is called it should save the state
    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "The onPause method is called", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = getPreferences(0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CHECKBOX_TEXT_VALUE",checkBox.getText().toString()); //Key is "CHECKBOX_TEXT_VALUE"
        editor.putBoolean("CHECKBOX_VALUE",checkBox.isChecked());
        editor.commit();  //Save the preferences

    }


    //When onResume method is called it should retreive the state
    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this,"The onResume method is called",Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = getPreferences(0); //Get the shared preferences
        boolean checkboxVal = sharedPreferences.getBoolean("CHECKBOX_VALUE",false);
        String checkboxStringVal = sharedPreferences.getString("CHECKBOX_TEXT_VALUE","This is my checkBox"); //Default value
        checkBox.setChecked(checkboxVal);
        checkBox.setText(checkboxStringVal);
    }
}
