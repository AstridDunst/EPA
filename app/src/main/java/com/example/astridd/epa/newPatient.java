package com.example.astridd.epa;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class newPatient extends AppCompatActivity {
    private SharedPreferences speicher;
    private SharedPreferences.Editor editor;
    final String scriptURLString = "http://epa.htl5.org/phpscripts/receive_skript.php";
    private int id_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (availableInternet()) {
                    Snackbar.make(view, "Internet verfügbar", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    Snackbar.make(view, "Keine Netzwerkverbindung möglich", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        getElements();
        firstrun();
    }
    @Override
    public void onBackPressed() {
    }
    private void getElements(){
        speicher = this.getSharedPreferences("Daten", MODE_PRIVATE);
        editor = speicher.edit();
    }
    public boolean availableInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
    private void firstrun(){

        this.setTitle("EPA");
        makenewDataSet(speicher.getString("speicher_standort", null),speicher.getString("speicher_nummer", null));
    }
    private void makenewDataSet(final String standort, final String stNummer)
    {
        //TODO: neuer Thread der den Patienten am Server anmeldet
        //neuen Thread anlegen dass das mit dem Internet nicht die ganze Activity nicht behindert
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = standort;
                    String st = stNummer;
                    //String textparam = "text1=" + URLEncoder.encode(s,"UTF-8");
                    String textparam = "standort=" + URLEncoder.encode(s,"UTF-8")+ "&stNummer="+URLEncoder.encode(st, "UTF-8");
                    URL scripturl = new URL(scriptURLString);
                    HttpURLConnection connection = (HttpURLConnection)scripturl.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setFixedLengthStreamingMode(textparam.getBytes().length);

                    OutputStreamWriter contentWriter = new OutputStreamWriter(connection.getOutputStream());
                    contentWriter.write(textparam);
                    contentWriter.flush();
                    contentWriter.close();

                    InputStream answerInputSteam = connection.getInputStream();
                    final String answer = getInputStream(answerInputSteam);
                    //Was an die Benutzeroberfläche zurückkommt
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!answer.equals("0")) {
                                id_number = Integer.valueOf(answer);
                                Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_SHORT).show();
                                //tv.setText(answer);
                            }else {
                                Toast.makeText(getApplicationContext(), "Probleme mit der Datenbank.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    answerInputSteam.close();
                    connection.disconnect();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public String getInputStream(InputStream is){
        BufferedReader reader =  new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String altuelleZeile;
        try {
            while ((altuelleZeile = reader.readLine()) != null){
                stringBuilder.append(altuelleZeile);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString().trim();
    }

}
