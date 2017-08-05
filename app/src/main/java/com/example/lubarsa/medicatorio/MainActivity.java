package com.example.lubarsa.medicatorio;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lubarsa on 13/07/17.
 */


public class MainActivity extends AppCompatActivity {

    TextView tvMedicatorioTitle;
    EditText etUserValidate;
    EditText etPasswordValidate;
    String userValidate;
    String passwordValidate;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface mainFont = Typeface.createFromAsset(getAssets(), "fonts/triforce.ttf");

        tvMedicatorioTitle = (TextView) findViewById(R.id.tvMedicatorio);
        tvMedicatorioTitle.setTypeface(mainFont);

        etUserValidate = (EditText) findViewById(R.id.etUser);

        etPasswordValidate = (EditText) findViewById(R.id.etPassword);
    }


    public void ValidateUser(View view) {

        userValidate = etUserValidate.getText().toString();
        passwordValidate = etPasswordValidate.getText().toString();

        if(userValidate.compareTo("Lubarsa")==0 && passwordValidate.compareTo("EO64")==0)
        {
            Intent intent = new Intent(this, ContainerActivity.class);
            startActivity(intent);

            finish();


        }
        else
        {
            Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_SHORT).show();
        }

    }
}
