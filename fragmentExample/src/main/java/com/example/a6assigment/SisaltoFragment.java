package com.example.a6assigment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SisaltoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private IsisaltoFragment mListener;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SisaltoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SisaltoFragment newInstance(String param1, String param2) {
        SisaltoFragment fragment = new SisaltoFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button buttoni;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sisalto, container, false);
        buttoni = v.findViewById(R.id.id_button);
        buttoni.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mListener.onNappiaPainettu(new IcallBack(){
                    @Override
                    public void TakaisinKutsu(int i) {

                    }
                });
            }
        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sisalto, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IsisaltoFragment) {
            mListener = (IsisaltoFragment) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface IcallBack{
        void TakaisinKutsu(int i);
    }
    public interface IsisaltoFragment{
        void onNappiaPainettu(IcallBack icallBack);
    }
}
