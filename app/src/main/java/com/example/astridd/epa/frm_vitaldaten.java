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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frm_vitaldaten.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frm_vitaldaten#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frm_vitaldaten extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private neuerFall mActivity;
    private  String patientennummer;
    private CheckBox cbOrientiert;
    private CheckBox cbDesorientiert;
    private CheckBox cbErregungszustand;
    private CheckBox cbOhneBewusstsein;
    private CheckBox cbTodeszeichen;
    private CheckBox cbGehend;
    private CheckBox cbSitzend;
    private CheckBox cbLiegend;
    private EditText tbVorgefundenSonstiges;
    private EditText tbGefahrenzone;
    private CheckBox cbKrampfgeschehen;
    private CheckBox cbSprachstoerungen;
    private EditText tbPupilleLinks;
    private EditText tbPupilleRechts;
    private EditText tbSchmerzbeurteilung;
    private CheckBox cbStarkeBlutung;
    private CheckBox cbArrythmie;
    private CheckBox cbSchockzustand;
    private CheckBox cbatemkreislaufstillstand;
    private CheckBox cbAtemwegUnauf;
    private CheckBox cbAtembeschwerden;
    private CheckBox cbAtemwegsverlegung;
    private CheckBox cbAtemger;
    private CheckBox cbAsybkBwg;
    private CheckBox cbKeineNormAtmung;
    private CheckBox cbHyperventilation;
    private EditText tbMEAF;
    private EditText tbMZAF;
    private EditText tbMESPO;
    private EditText tbMZSPO;
    private EditText tbMEHF;
    private EditText tbMZHF;
    private EditText tbMERR;
    private EditText tbMZRR;
    private EditText tbMEBZ;
    private EditText tbMZBZ;
    private EditText tbMEC;
    private EditText tbMZC;
    private EditText tbVerdachtsdiagnose;
    private EditText cbVerbrennung;
    private EditText cbErfrierung;
    private EditText cbveraetzung;




    //waduup astridd

    private OnFragmentInteractionListener mListener;

       private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {

           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if (!hasFocus) {
                 /*  if (v.getId() == tbVorgefundenSonstiges.getId()) {
                       updateDataset("p_orientiert", String.valueOf(cbOrientiert.get));

                   }*/
               }
                   if (v.getId()== tbVorgefundenSonstiges.getId()){
                        updateDataset("p_vorgefundensonstiges",String.valueOf(tbVorgefundenSonstiges.getText()),"p_patientenlagebeurteilung");
                    }else if (v.getId()==tbGefahrenzone.getId()) {
                       updateDataset("p_gefahrenzone", String.valueOf(tbGefahrenzone.getText()), "p_patientenlagebeurteilung");
                   }else if(v.getId()==tbPupilleLinks.getId()){
                       updateDataset("n_pupillelink",String.valueOf(tbPupilleLinks.getText()), "n_neurologie");
                   }else if(v.getId()==tbPupilleRechts.getId()){
                       updateDataset("n_pupillerechts",String.valueOf(tbPupilleRechts.getText()),"n_neurologie");
                   }else if(v.getId()==tbSchmerzbeurteilung.getId()){
                       updateDataset("n_schmerzbeurteilung",String.valueOf(tbSchmerzbeurteilung.getText()),"n_neurologie");
                   }else if(v.getId()==tbMEAF.getId()){
                       updateDataset("f_messungAF1",String.valueOf(tbMEAF.getText()),"f_fall");
                   }else if(v.getId()==tbMZAF.getId()){
                       updateDataset("f_messungAF2",String.valueOf(tbMZAF.getText()),"f_fall");
                   }else if(v.getId()==tbMESPO.getId()){
                       updateDataset("f_messungSPO1",String.valueOf(tbMESPO.getText()),"f_fall");
                   }else if(v.getId()==tbMZSPO.getId()){
                       updateDataset("f_messungSPO2",String.valueOf(tbMZSPO.getText()),"f_fall");
                   } else if (v.getId() == tbMEHF.getId()) {
                       updateDataset("f_messungHF1",String.valueOf(tbMEHF.getText()),"f_fall");
                   } else if (v.getId() == tbMZHF.getId()) {
                       updateDataset("f_messungHF2",String.valueOf(tbMZHF.getText()),"f_fall");
                   }else if(v.getId()==tbMERR.getId()){
                       updateDataset("f_messungRR1",String.valueOf(tbMERR.getText()),"f_fall");
                   } else if (v.getId() == tbMZRR.getId()) {
                       updateDataset("f_messungRR2",String.valueOf(tbMZRR.getText()),"f_fall");
                   } else if (v.getId() == tbMEBZ.getId()) {
                       updateDataset("f_messungBZ1",String.valueOf(tbMEBZ.getText()),"f_fall");
                   } else if (v.getId() == tbMZBZ.getId()) {
                       updateDataset("f_messungBZ2",String.valueOf(tbMZBZ.getText()),"f_fall");
                   }else if (v.getId() == tbMEC.getId()) {
                       updateDataset("f_messungC1",String.valueOf(tbMEC.getText()),"f_fall");
                   }else if (v.getId() == tbMZC.getId()) {
                       updateDataset("f_messungC2",String.valueOf(tbMZC.getText()),"f_fall");
                   }else if (v.getId() == tbVerdachtsdiagnose.getId()) {
                       updateDataset("f_verdachtsdiagnose",String.valueOf(tbVerdachtsdiagnose.getText()),"f_fall");
                   }else if (v.getId() == cbVerbrennung.getId()) {
                       updateDataset("k_verbrennung",String.valueOf(cbVerbrennung.getText()),"k_kreislauf");
                   }else if (v.getId() == cbErfrierung.getId()) {
                       updateDataset("k_erfrierung",String.valueOf(cbErfrierung.getText()),"k_kreislauf");
                   }else if (v.getId() == cbveraetzung.getId()) {
                       updateDataset("k_veraetzung",String.valueOf(cbveraetzung.getText()),"k_kreislauf");
                   }
           }
       };

              public frm_vitaldaten() {
          // Required empty public constructor
      }

      /**
       * Use this factory method to create a new instance of
       * this fragment using the provided parameters.
       *
       * @param param1 Parameter 1.
       * @param param2 Parameter 2.
       * @return A new instance of fragment frm_vitaldaten.
       */
    // TODO: Rename and change types and number of parameters
    public static frm_vitaldaten newInstance(String param1, String param2) {
        frm_vitaldaten fragment = new frm_vitaldaten();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private void updateDataset(String fieldname, String content, String table){

        mActivity.updateDataset(fieldname,content,table);


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
        View view = inflater.inflate(R.layout.fragment_frm_vitaldaten, container, false);
        getElements(view);
        setListeners();

        // Inflate the layout for this fragment
        //Toast.makeText(getActivity(), "Code Teil 2", Toast.LENGTH_SHORT).show();
        return view;
    }
    private void getElements(View view) {
        //Step2:
        cbOrientiert = (CheckBox)view.findViewById(R.id.cbOrientiert);

        //Checkboxen
        tbVorgefundenSonstiges = (EditText) view.findViewById(R.id.tbVorgefundenSonstiges);
        tbGefahrenzone = (EditText) view.findViewById(R.id.tbGefahrenzone);
        //tbVorgefundenSonstiges = (EditText) view.findViewById(R.id.tbVorgefundenSonstiges);
        tbGefahrenzone = (EditText) view.findViewById(R.id.tbGefahrenzone);
        tbPupilleLinks = (EditText) view.findViewById(R.id.tbPupilleLinks);
        tbPupilleRechts = (EditText) view.findViewById(R.id.tbPupilleRechts);
        tbSchmerzbeurteilung = (EditText) view.findViewById(R.id.tbSchmerzbeurteilung);
        tbMEAF = (EditText) view.findViewById(R.id.tbMEAF);
        tbMZAF = (EditText)view.findViewById(R.id.tbMZAF);
        tbMESPO = (EditText)view.findViewById(R.id.tbMESPO);
        tbMZSPO = (EditText)view.findViewById(R.id.tbMZSPO);
        tbMEHF = (EditText)view.findViewById(R.id.tbMEHF);
        tbMZHF = (EditText)view.findViewById(R.id.tbMZHF);
        tbMERR = (EditText)view.findViewById(R.id.tbMERR);
        tbMZRR = (EditText)view.findViewById(R.id.tbMZRR);
        tbMEBZ = (EditText)view.findViewById(R.id.tbMEBZ);
        tbMZBZ = (EditText)view.findViewById(R.id.tbMZBZ);
        tbMEC = (EditText)view.findViewById(R.id.tbMEC);
        tbMZC = (EditText)view.findViewById(R.id.tbMZC);
        tbVerdachtsdiagnose=(EditText)view.findViewById(R.id.tbVerdachtsdiagnose);
        cbVerbrennung = (EditText)view.findViewById(R.id.cbVerbrennung);
        cbErfrierung = (EditText)view.findViewById(R.id.cbErfrierung);
        cbveraetzung = (EditText)view.findViewById(R.id.cbveraetzung);
        //cbveraetzung = (EditText)view.findViewById(R.id.cbveraetzung);
        //tbHeimlich = (EditText) view.findViewById(R.id.tbHeimlich);

    }

    private void setListeners() {
        //Step3:
        //cbOrientiert.setOnFocusChangeListener(focusChangeListener);
        cbVerbrennung.setOnFocusChangeListener(focusChangeListener);
        cbErfrierung.setOnFocusChangeListener(focusChangeListener);
        cbveraetzung.setOnFocusChangeListener(focusChangeListener);
        tbVorgefundenSonstiges.setOnFocusChangeListener(focusChangeListener);
        tbGefahrenzone.setOnFocusChangeListener(focusChangeListener);
        tbVorgefundenSonstiges.setOnFocusChangeListener(focusChangeListener);
        tbGefahrenzone.setOnFocusChangeListener(focusChangeListener);
        tbPupilleLinks.setOnFocusChangeListener(focusChangeListener);
        tbPupilleRechts.setOnFocusChangeListener(focusChangeListener);
        tbSchmerzbeurteilung.setOnFocusChangeListener(focusChangeListener);
        tbMEAF.setOnFocusChangeListener(focusChangeListener);
        tbMZAF.setOnFocusChangeListener(focusChangeListener);
        tbMESPO.setOnFocusChangeListener(focusChangeListener);
        tbMZSPO.setOnFocusChangeListener(focusChangeListener);
        tbMEHF.setOnFocusChangeListener(focusChangeListener);
        tbMZHF.setOnFocusChangeListener(focusChangeListener);
        tbMERR.setOnFocusChangeListener(focusChangeListener);
        tbMZRR.setOnFocusChangeListener(focusChangeListener);
        tbMEBZ.setOnFocusChangeListener(focusChangeListener);
        tbMZBZ.setOnFocusChangeListener(focusChangeListener);
        tbMEC.setOnFocusChangeListener(focusChangeListener);
        tbMZC.setOnFocusChangeListener(focusChangeListener);
        tbVerdachtsdiagnose.setOnFocusChangeListener(focusChangeListener);
        //tbHeimlich.setOnFocusChangeListener(focusChangeListener);
        cbOrientiert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                if (isChecked == true){
                    updateDataset("p_orientiert", String.valueOf(1), "p_patientenlagebeurteilung");
                }
                else {
                    updateDataset("p_orientiert", String.valueOf(0), "p_patientenlagebeurteilung");
                }
            }
        }
        );
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
