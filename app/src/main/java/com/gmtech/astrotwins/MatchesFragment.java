package com.gmtech.astrotwins;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup.LayoutParams;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


public class MatchesFragment extends Fragment {

    public MatchesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_matches, container, false);

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.filter_place_layout);

        Window window = dialog.getWindow();
        window.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);

        ImageButton filter = (ImageButton) rootview.findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        ImageButton exit = (ImageButton) dialog.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ListView listView;
        listView = dialog.findViewById(R.id.place);
        String[] eText = getResources().getStringArray(R.array.places);
        EditText placename = dialog.findViewById(R.id.placetext);

        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1 ,eText );
        listView.setAdapter(arrayAdapter);
        listView.setTextFilterEnabled(true);

        placename.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                arrayAdapter.getFilter().filter(editable);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                placename.setText(eText[(int)l]);
            }
        });

        Button ok = dialog.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        return rootview;
    }
}