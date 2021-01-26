package com.ibso.student_predict_course;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserDetailsFragment extends Fragment {
    Spinner spinner_medu,spinner_fedu,spinner_mjob,spinner_fjob,spinner_studytime;
    ArrayAdapter<CharSequence> job_options_adapter, education_adapter,studytime_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userdetails, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner_medu = view.findViewById(R.id.spinner_medu);
        spinner_fedu = view.findViewById(R.id.spinner_fedu);
        spinner_mjob = view.findViewById(R.id.spinner_mjob);
        spinner_fjob = view.findViewById(R.id.spinner_fjob);
        spinner_studytime = view.findViewById(R.id.spinner_studytime);

        job_options_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.job_options_array, android.R.layout.simple_spinner_item);
        job_options_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mjob.setAdapter(job_options_adapter);
        spinner_fjob.setAdapter(job_options_adapter);

        education_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.education_array, android.R.layout.simple_spinner_item);
        education_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_fedu.setAdapter(education_adapter);
        spinner_medu.setAdapter(education_adapter);

        studytime_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.studytime_array, android.R.layout.simple_spinner_item);
        education_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_studytime.setAdapter(education_adapter);
    }
}
