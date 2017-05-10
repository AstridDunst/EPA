package com.example.astridd.epa;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fmassnahme3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fmassnahme3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fmassnahme3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private neuerFall mActivity;
    private OnFragmentInteractionListener mListener;
    //Step1:
    private CheckBox cbHelmabnahme;
    private CheckBox cbAbsaugung;
    private CheckBox cbesmarchhandgriff;
    private CheckBox cbGuedeltubus;
    private CheckBox cbLarnixtubus;
    private CheckBox cbBeatumung;
    private CheckBox cbRueckatmung;
    private CheckBox cbBeatmung;
    private EditText tbEkg;
    private EditText tbInfusion;
    private EditText tbHerzDruck;
    private CheckBox cbDefi;
    private CheckBox cbBlutstillung;
    private EditText tbKreislaufSonst;
    private CheckBox cbRautegriff;
    private CheckBox cbRettungstuch;
    private CheckBox cbSchaufeltrage;
    private CheckBox cbSpineboard;
    private CheckBox cbHwsSchienung;
    private CheckBox cbExtremitätenschienung;
    private CheckBox cbVakuummatraze;
    private CheckBox cbRettungskorsett;
    private CheckBox cbArztanwesend;
    private CheckBox cbAmputation;
    private EditText tbAmputationstext;
    private CheckBox cbAugenspuelung;
    private CheckBox cbEntbindung;
    private EditText tbArztname;


    public fmassnahme3() {
        // Required empty public constructor
    }

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            Toast.makeText(getActivity(), "CBBeatmung", Toast.LENGTH_SHORT).show();
            if (!hasFocus) {

                //TODO: TEXTFELDER KEINE CHECKBOXEN
                if (v.getId() == cbBeatmung.getId()) {
                    //checkcomment


                    if (cbBeatmung.isChecked()) {
                        updateDataset("m_beatmung", "1", "m_massnahmen");
                    } /*else if {
                        updateDataset("m_beatmung", "0", "m_massnahmen");
                    }
*/
                    if (v.getId() == tbEkg.getId()) {
                        updateDataset("m_ekg",String.valueOf(tbEkg.getText()),"m_massnahmen");
                    }else if(v.getId()==tbInfusion.getId()){
                        updateDataset("m_infusion",String.valueOf(tbInfusion.getText()),"m_massnahmen");
                    }else if(v.getId()==tbHerzDruck.getId()){
                        updateDataset("m_herzdruckmassage",String.valueOf(tbHerzDruck.getText()),"m_massnahmen");
                    } else if (v.getId() == tbKreislaufSonst.getId()) {
                        updateDataset("m_ksSonstige",String.valueOf(tbKreislaufSonst.getText()),"m_massnahmen");
                    } else if (v.getId() == tbAmputationstext.getId()) {
                        updateDataset("m_amputationext",String.valueOf(tbAmputationstext.getText()),"m_massnahmen");
                    } else if (v.getId() == tbArztname.getId()) {
                        updateDataset("m_arztnahme",String.valueOf(tbArztname.getText()),"m_massnahme");
                    }
                }
                 }


        }
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fmassnahme3.
     */
    // TODO: Rename and change types and number of parameters
    public static fmassnahme3 newInstance(String param1, String param2) {
        fmassnahme3 fragment = new fmassnahme3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch(buttonView.getId()){
            case R.id.cbBeatmung:
                //do stuff
                if (cbBeatmung.isChecked()) {
                    updateDataset("m_beatmung", "1", "m_massnahmen");
                } else {
                    updateDataset("m_beatmung", "0", "m_massnahmen");
                }
                break;
            //case R.id.marathon_checkbox:
                //do stuff
                //break;
            //case R.id.never_ending_checkbox:
                //do stuff
                //break;

        }

    }
    private void getElements(View view){
        //Step2:
        cbBeatmung = (CheckBox) view.findViewById(R.id.cbBeatmung);
        cbHelmabnahme = (CheckBox) view.findViewById(R.id.cbHelmabnahme);
        cbAbsaugung = (CheckBox) view.findViewById(R.id.cbAbsaugung);
        tbEkg = (EditText)view.findViewById(R.id.tbEkg);
        tbInfusion=(EditText)view.findViewById(R.id.tbInfusion);
        tbHerzDruck=(EditText)view.findViewById(R.id.tbHerzdruck);
        tbKreislaufSonst=(EditText)view.findViewById(R.id.tbKreislaufSonst);
        tbAmputationstext=(EditText)view.findViewById(R.id.tbAmputationstext);
        tbArztname = (EditText)view.findViewById(R.id.tbArztname);

    }
    private void setListeners(){
        //Step3:
        //cbBeatmung.setOnCheckedChangeListener(onCheckedChanged);
        tbEkg.setOnFocusChangeListener(focusChangeListener);
        tbInfusion.setOnFocusChangeListener(focusChangeListener);
        tbHerzDruck.setOnFocusChangeListener(focusChangeListener);
        tbKreislaufSonst.setOnFocusChangeListener(focusChangeListener);
        tbAmputationstext.setOnFocusChangeListener(focusChangeListener);
        tbArztname.setOnFocusChangeListener(focusChangeListener);


        //Checkboxen
        cbHelmabnahme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                if (isChecked == true){
                    updateDataset("m_helmabnahme", String.valueOf(1), "m_massnahmen");
                }
                else {
                    updateDataset("m_helmabnahme", String.valueOf(0), "m_massnahmen");
                }
            }}
        );


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Toast.makeText(getActivity(), "Code Massnahme", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_fmassnahme3, container, false);
        getElements(view);
        setListeners();
        return view;
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
    private void updateDataset(String fieldname, String content, String table){

        mActivity.updateDataset(fieldname,content,table);


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
