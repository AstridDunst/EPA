package com.example.astridd.epa;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
    private  String patientennummer;
    final String scriptURLString = "http://epa.htl5.org/phpscripts/update_script.php";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText tbVorname;
    private EditText tbNachname;

    private OnFragmentInteractionListener mListener;

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {


            if(!hasFocus){
                if (v.getId() == tbVorname.getId()) {
                    //Erstes Feld Datenbankname
                    //Zweites Feld Content
                    updateDataset("f_vorname",String.valueOf(tbNachname.getText()));
                }else if (v.getId()==tbNachname.getId()){
                    updateDataset("f_zuname",String.valueOf(tbNachname.getText()));
                }
            }

        }
    };
    private void updateDataset(String fieldname, String content){

        //neuerFall nf = new neuerFall();
        //Toast.makeText(getActivity(), "bin da", Toast.LENGTH_SHORT).show();
        //nf.updateDataset(Fieldname,Content);

        //try
        //{
            /*String username = "epa";
            String password = "epateam";
            String url = "jdbc:mysql://epa.htl5.org:3306/dunst_epa?connectTimeout=3000";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);*/


            // create the java mysql update preparedstatement
            //String query = "update users set num_points = ? where first_name = ?";
            //String query = "UPDATE f_fall SET " + fieldname +  " = " + content+ " WHERE (f_id ="+ patientennummer+");";
           // PreparedStatement preparedStmt = conn.prepareStatement(query);
            //preparedStmt.setInt   (1, 6000);
            //preparedStmt.setString(2, "Fred");
            //Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
            // execute the java preparedstatement
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
          //  preparedStmt.executeUpdate();

            //Connection con= DriverManager.getConnection(
                    //"jdbc:mysql://localhost:3306/","root","root");

            //Toast.makeText(getActivity(), (CharSequence) con.getMetaData(), Toast.LENGTH_SHORT).show();
        //}
        //catch (SQLException e)
        //{
            //Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            //System.err.println("Got an exception! ");
            //System.err.println(e.getMessage());
        //}



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
        tbVorname = (EditText)view.findViewById(R.id.etVorname);
        tbNachname = (EditText)view.findViewById(R.id.edNachname);
    }
    private void setListeners(){
        tbVorname.setOnFocusChangeListener(focusChangeListener);
        tbNachname.setOnFocusChangeListener(focusChangeListener);
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
