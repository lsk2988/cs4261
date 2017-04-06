package com.example.shuangke.weproject2;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageButton bt = (ImageButton)view.findViewById(R.id.more);
        bt.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), AllCategories.class);
               startActivity(intent);
           }
        });
        return view;
    }




}
