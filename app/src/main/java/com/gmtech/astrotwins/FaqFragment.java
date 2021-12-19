package com.gmtech.astrotwins;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class FaqFragment extends Fragment {

    public FaqFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_faq, container, false);
        ExpandableTextView expandableTextView = rootView.findViewById(R.id.question_1_view);
        expandableTextView.setText(getString(R.string.placeholder_text));
        return rootView;
    }
}