package com.example.truenoblanco.proyecto1;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Proyecto1Activity extends ActionBarActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyecto1);
        helper.lleanarBD();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.Blogin)
        {
            EditText a = (EditText)findViewById(R.id.TFusername);
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.TFpassword);
            String pass = b.getText().toString();
            String password = helper.searchPass(str);

            if ( str.equals("") || pass.equals("")){
                Toast temp = Toast.makeText(this, "Ingrese los campos mostrados!", Toast.LENGTH_SHORT);
                temp.show();
            }
            else {

                if(pass.equals(password))
                {
                    Intent i = new Intent(this, Proyecto1Activity1.class);
                    i.putExtra("Username",str);
                    startActivity(i);
                }
                else
                {
                    Toast temp = Toast.makeText(this, "Usuario y contrasena incorrecto!", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }



        }
        if(v.getId() == R.id.Bsignup)
        {
            Intent i = new Intent(this, SingUp.class);
            startActivity(i);

        }

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


}
