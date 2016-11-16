package com.example.astridd.epa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Erstanmeldung extends AppCompatActivity {

    Button bt_fertig;
    EditText tb_Standort;
    EditText tb_Nummer;
    private SharedPreferences speicher;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erstanmeldung);
        getElementsbyID();
    }
    @Override
    public void onBackPressed() {

    }
    private void getElementsbyID() {
        bt_fertig = (Button) findViewById(R.id.bt_fertig);
        tb_Standort = (EditText) findViewById(R.id.etStandort);
        tb_Nummer = (EditText) findViewById(R.id.et_Nummer);
        speicher = this.getSharedPreferences("Daten", MODE_PRIVATE);
        editor = speicher.edit();
    }

    public void btfertig_onClick(View view){
        String strFertig = tb_Standort.getText().toString();
        String strNummer = tb_Nummer.getText().toString();

        Toast.makeText(getApplicationContext(),strFertig, Toast.LENGTH_LONG);

        if ((strFertig.equals(""))){
            Toast.makeText(getApplicationContext(),"Standort darf nicht leer bleiben.", Toast.LENGTH_LONG).show();
        } else if( (strNummer.equals(""))){
            Toast.makeText(getApplicationContext(),"Nummer muss angegeben werden", Toast.LENGTH_LONG).show();
        } else {
            //SharedPreferences eingabe
            editor.putString("speicher_standort", strFertig);
            editor.commit();
            editor.putString("speicher_nummer", strNummer);
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
