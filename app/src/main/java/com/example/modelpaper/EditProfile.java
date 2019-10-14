package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.modelpaper.Database.DBHandler;

public class EditProfile extends AppCompatActivity {

    EditText editText_username, editText_password, editText_dob;
    RadioButton radioButton_male, radioButton_female;
    DBHandler dbHandler;
    String user_id;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editText_username = findViewById(R.id.username_profile);
        editText_password = findViewById(R.id.password_profile);
        editText_dob = findViewById(R.id.dob_profile);

        radioButton_male = findViewById(R.id.radio_male);
        radioButton_female = findViewById(R.id.radio_female);

        dbHandler = new DBHandler(this);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked)
                    gender = "Male";
                break;
            case R.id.radio_female:
                if (checked)
                    gender = "Female";
                break;
        }
    }


    public void search(View view) {
        String id = "2";
        Cursor cursor = dbHandler.readAllInfor(id);

        if(cursor.getCount()==0){
            Toast.makeText(this, "No any user!! ", Toast.LENGTH_SHORT).show();
            return;
        }

        while(cursor.moveToNext()){
            //Set data
            user_id = String.valueOf(cursor.getInt(0));
            editText_username.setText(cursor.getString(1));
            editText_dob.setText(cursor.getString(3));
            editText_password.setText(cursor.getString(2));

            if(cursor.getString(cursor.getColumnIndex(UserProfile.Users.COLUMN_NAME_GENDER)).equals("male")){
                    radioButton_male.setChecked(true);
                    radioButton_female.setChecked(false);
            }
            else
            {
                radioButton_male.setChecked(false);
                radioButton_female.setChecked(true);
            }
        }

        cursor.close();
    }

    public void edit(View view) {
        String username = editText_username.getText().toString();
        String password = editText_password.getText().toString();
        String dob = editText_dob.getText().toString();

        boolean result = dbHandler.updateInfor(username, password, dob, gender, user_id);

        if(result == true){
            Toast.makeText(this, "Data updated!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Data cannot be updated!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view) {
        boolean result = dbHandler.deleteInfor(user_id);

        if(result){
            Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
            user_id = null;
            gender = null;
            editText_username.setText("");
            editText_password.setText("");
            editText_dob.setText("");
        }
        else
        {
            Toast.makeText(this, "Cannot be deleted!", Toast.LENGTH_SHORT).show();
        }

    }
}
