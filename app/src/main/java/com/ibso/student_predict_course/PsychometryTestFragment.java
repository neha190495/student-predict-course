package com.ibso.student_predict_course;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PsychometryTestFragment extends Fragment {

    private onFragmentButtonSelected listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_psychometrytest, container, false);
        Button takePsychometryTest = view.findViewById(R.id.button_takepsychometryTest);
        takePsychometryTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPsychometryTestButtonClick();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onFragmentButtonSelected){
            listener = (onFragmentButtonSelected) context;
        }else {
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    public interface onFragmentButtonSelected{
        public void onPsychometryTestButtonClick();
    }
}
