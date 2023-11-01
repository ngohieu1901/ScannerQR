package com.example.scannerqr.UI.qrcode.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.scannerqr.R;

public class ContactFragment extends Fragment {
    FragmentManager manager;
    FrameLayout layout_person, layout_company;
    LinearLayout layout_container;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.frag_contact,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layout_person = view.findViewById(R.id.layout_person);
        layout_company = view.findViewById(R.id.layout_company);
        layout_container = view.findViewById(R.id.layout_container);

        layout_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_container.setVisibility(View.GONE);
                ContactPerSonFragment contactPerSonFragment = new ContactPerSonFragment();
                manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container_view,contactPerSonFragment).commit();
            }
        });

        layout_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_container.setVisibility(View.GONE);
                ContactCompanyFragment contactCompanyFragment = new ContactCompanyFragment();
                manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container_view,contactCompanyFragment).commit();
            }
        });


    }
}
