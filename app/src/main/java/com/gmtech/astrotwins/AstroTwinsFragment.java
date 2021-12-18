package com.gmtech.astrotwins;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AstroTwinsFragment extends Fragment {
    TextView register;
    ImageButton next,prev;
    Dialog okay;
    Button connect,ok;

    public AstroTwinsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_astro_twins, container, false);
        register = rootView.findViewById(R.id.back_to_register);
        next = rootView.findViewById(R.id.next);
        prev = rootView.findViewById(R.id.previous);
        okay = new Dialog(getActivity());
        connect = rootView.findViewById(R.id.connect);

        Window window = okay.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        okay.setContentView(R.layout.friend_request_layout);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                okay.show();
            }
        });

        ok = okay.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                okay.dismiss();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prev.setVisibility(View.VISIBLE);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),RegisterActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}