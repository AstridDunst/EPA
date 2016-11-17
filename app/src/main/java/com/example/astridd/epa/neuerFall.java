package com.example.astridd.epa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

public class neuerFall extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SharedPreferences speicher;
    private SharedPreferences.Editor editor;
    final String scriptURLString = "http://epa.htl5.org/phpscripts/receive_skript.php";
    final String abscriptURLString = "http://epa.htl5.org/phpscripts/logout_skript.php";
    private int id_number;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private EditText tbVorname;
    private EditText tbNachname;

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            Toast.makeText(getApplicationContext(), "Something happend", Toast.LENGTH_SHORT).show();
            if(!hasFocus){
                Toast.makeText(getApplicationContext(), "Focus lost", Toast.LENGTH_SHORT).show();
            }
        }
    };
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuer_fall);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         //Initialise Components


        getElements();
        firstrun();

    }









    private void findElements(){
        //TODO: Initialise Compoments
        tbVorname = (EditText) findViewById(R.id.etVorname);
        tbNachname = (EditText) findViewById(R.id.edNachname);

    }
    private void setAllListeners(){
        View.OnFocusChangeListener fcl= focusChangeListener;/*new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Toast.makeText(getApplicationContext(), "Something happend", Toast.LENGTH_SHORT).show();
                if(!hasFocus){
                    Toast.makeText(getApplicationContext(), "Focus lost", Toast.LENGTH_SHORT).show();
                }
            }
        };*/
        //TODO: Set all Listeners
        //tbVorname.setOnFocusChangeListener(fcl);
        //tbNachname.setOnFocusChangeListener(fcl);
        tbVorname.setText("T");

    }

    private void firstrun(){
        if (availableInternet()){
            this.setTitle("EPA");
            LoginToServer(speicher.getString("speicher_standort", null),speicher.getString("speicher_nummer", null));
        }
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
    private void LoginToServer(final String standort, final String stNummer){
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_neuer_fall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //wenn abmelden soll
            vomServerabmelden();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void vomServerabmelden(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = String.valueOf(id_number);
                    //String textparam = "text1=" + URLEncoder.encode(s,"UTF-8");
                    String textparam = "nummer=" + URLEncoder.encode(s,"UTF-8");
                    URL scripturl = new URL(abscriptURLString);
                    HttpURLConnection connection = (HttpURLConnection)scripturl.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setFixedLengthStreamingMode(textparam.getBytes().length);

                    final OutputStreamWriter contentWriter = new OutputStreamWriter(connection.getOutputStream());
                    contentWriter.write(textparam);
                    contentWriter.flush();
                    contentWriter.close();

                    InputStream answerInputSteam = connection.getInputStream();
                    final String answer = getInputStream(answerInputSteam);
                    //Was an die Benutzeroberfläche zurückkommt
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (answer.equals("true")) {
                                //id_number = Integer.valueOf(answer);
                                tre();
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    private void tre(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                if (getArguments().getInt(ARG_SECTION_NUMBER)==2){
                    View rootView = inflater.inflate(R.layout.fragment_fallgemein2,container,false);

                    return rootView;
                }else if (getArguments().getInt(ARG_SECTION_NUMBER)==3){
                    View rootView = inflater.inflate(R.layout.fragment_fmassnahme3,container,false);

                    return rootView;
                }else {
                    View rootView = inflater.inflate(R.layout.fragment_stammdaten, container, false);
                    //tbVorname = (EditText)rootView.findViewById(R.id.etVorname);
                    //tbVorname.setText("Blablabla");

                    return rootView;
                }

            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            //return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

                return PlaceholderFragment.newInstance(position + 1);


        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Stammdaten";
                case 1:
                    return "Allgemeines";
                case 2:
                    return "Maßnahmen";
            }
            return null;
        }

    }
}
