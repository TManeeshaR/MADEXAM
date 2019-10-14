package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.modelpaper.Database.DBHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText editText_username, editText_password, editText_dob;
    RadioButton radioButton_male, radioButton_female;
    DBHandler dbHandler;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        dbHandler = new DBHandler(this);

        editText_username = findViewById(R.id.username_profile);
        editText_password = findViewById(R.id.password_profile);
        editText_dob = findViewById(R.id.dob_profile);

        radioButton_female = findViewById(R.id.radio_female);
        radioButton_male = findViewById(R.id.radio_male);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_male:
                if (checked)
                    gender = "male";
                break;
            case R.id.radio_female:
                if (checked)
                    gender = "female";
                break;
        }
    }

    public void update(View view) {
        Intent intent = new Intent(ProfileManagement.this, EditProfile.class);
        startActivity(intent);
    }

    public void addInfo(View view) {
        String username = editText_username.getText().toString();
        String dateOfBirth = editText_dob.getText().toString();
        String password = editText_password.getText().toString();

        boolean result = dbHandler.addInfo(username, password, dateOfBirth, gender);

        if (result)
            Toast.makeText(this, "Data Added Successfully!", Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(this, "Error! Data can't add!", Toast.LENGTH_SHORT).show();
    }
}
