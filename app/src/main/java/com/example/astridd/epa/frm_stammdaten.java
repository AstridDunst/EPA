package com.example.astridd.epa;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frm_stammdaten.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frm_stammdaten#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frm_stammdaten extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private neuerFall mActivity;
    private  String patientennummer;
    final String scriptURLString = "http://epa.htl5.org/phpscripts/update_script.php";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Step 1:
    private EditText tbVorname;
    private EditText tbNachname;
    private EditText tbTitel;
    private EditText tbStrasse;
    private EditText tbPlz;
    private EditText tbOrt;
    private EditText tbGebdat;
    private EditText tbRisikofaktoren;
    private EditText tbTransportdat;
    private EditText tbVorgeschehen;
    private EditText tbPatGes;

    private OnFragmentInteractionListener mListener;






    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            //es hat funktioniert
            if(!hasFocus){
                //Für Romi: Listener für jede Box setzen
                //Zuerst Feldname dann Content dann Datenbank
                //Step 4:
                if (v.getId() == tbVorname.getId()) {
                    //Erstes Feld Datenbankname
                    //Zweites Feld Content
                    updateDataset("f_vorname",String.valueOf(tbVorname.getText()),"f_fall");
                }else if (v.getId()==tbNachname.getId()){
                    updateDataset("f_zuname",String.valueOf(tbNachname.getText()),"f_fall");
                }else if (v.getId()==tbTitel.getId()){
                    updateDataset("f_titel",String.valueOf(tbTitel.getText()),"f_fall");
                }else if (v.getId()==tbStrasse.getId()){
                    updateDataset("f_strasse",String.valueOf(tbStrasse.getText()),"f_fall");
                }else if (v.getId()==tbPlz.getId()){
                    updateDataset("f_plz",String.valueOf(tbPlz.getText()),"f_fall");
                }else if (v.getId()==tbOrt.getId()){
                    updateDataset("f_ort",String.valueOf(tbOrt.getText()),"f_fall");
                }else if(v.getId()==tbGebdat.getId()){
                    updateDataset("f_geb",String.valueOf(tbGebdat.getText()),"f_fall");
                }else if(v.getId()==tbRisikofaktoren.getId()){
                    updateDataset("f_risikofaktoren",String.valueOf(tbRisikofaktoren.getText()),"f_fall");
                }else if(v.getId()==tbTransportdat.getId()){
                    updateDataset("f_transportdat",String.valueOf(tbTransportdat.getText()),"f_fall");
                }else if(v.getId()==tbVorgeschehen.getId()){
                    updateDataset("f_vorgeschehen",String.valueOf(tbVorgeschehen.getText()),"f_fall");
                }else if(v.getId()==tbPatGes.getId()){
                    updateDataset("f_patgeschichte",String.valueOf(tbPatGes.getText()),"f_fall");
                }
            }

        }
    };
    private void updateDataset(String fieldname, String content, String table){

        mActivity.updateDataset(fieldname,content,table);


    }


    public frm_stammdaten() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frm_stammdaten.
     */
    // TODO: Rename and change types and number of parameters
    public static frm_stammdaten newInstance(String param1, String param2) {
        frm_stammdaten fragment = new frm_stammdaten();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            patientennummer = getArguments().getString(ARG_PARAM1);
            Toast.makeText(getActivity(), "Patientennummer im Fragment" + patientennummer, Toast.LENGTH_SHORT).show();
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stammdaten, container, false);
        getElements(view);
        setListeners();
        return view;

    }
    private void getElements(View view){
        //Step2:
        tbVorname = (EditText)view.findViewById(R.id.tbVorname);
        tbNachname = (EditText)view.findViewById(R.id.tbNachname);
        tbTitel = (EditText)view.findViewById(R.id.tbTitel);
        tbStrasse = (EditText)view.findViewById(R.id.tbStrasse);
        tbPlz = (EditText)view.findViewById(R.id.tbPlz);
        tbOrt = (EditText)view.findViewById(R.id.tbOrt);
        tbGebdat =(EditText)view.findViewById(R.id.tbGebdat);
        tbRisikofaktoren = (EditText)view.findViewById(R.id.tbRisikofaktoren);
        tbTransportdat = (EditText)view.findViewById(R.id.tbTransportdat);
        tbVorgeschehen = (EditText)view.findViewById(R.id.tbVorgeschehen);
        tbPatGes = (EditText)view.findViewById(R.id.tbPatGes);
    }
    private void setListeners(){
        //Step3:
        tbVorname.setOnFocusChangeListener(focusChangeListener);
        tbNachname.setOnFocusChangeListener(focusChangeListener);
        tbTitel.setOnFocusChangeListener(focusChangeListener);
        tbStrasse.setOnFocusChangeListener(focusChangeListener);
        tbPlz.setOnFocusChangeListener(focusChangeListener);
        tbOrt.setOnFocusChangeListener(focusChangeListener);
        tbGebdat.setOnFocusChangeListener(focusChangeListener);
        tbRisikofaktoren.setOnFocusChangeListener(focusChangeListener);
        tbTransportdat.setOnFocusChangeListener(focusChangeListener);
        tbVorgeschehen.setOnFocusChangeListener(focusChangeListener);
        tbPatGes.setOnFocusChangeListener(focusChangeListener);
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (neuerFall) context;


        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
