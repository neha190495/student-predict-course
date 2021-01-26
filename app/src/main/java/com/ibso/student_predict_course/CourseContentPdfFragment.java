package com.ibso.student_predict_course;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CourseContentPdfFragment extends Fragment {

    private PDFView pdfView;
    private String mPDFName;
    private FloatingActionButton fab_main, fab1_test, fab2_ar;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    TextView textview_test, textview_ar;

    Boolean isOpen = false;

    private CourseContentPdfFragment.onCourseContentPdfFragmentButtonSelected listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coursecontentpdf, container, false);
        mPDFName = getArguments().getString("PDF_NAME");

        fab_main = view.findViewById(R.id.fab_menu_pdffragment);
        fab1_test = view.findViewById(R.id.fab_test);
        fab2_ar = view.findViewById(R.id.fab_ar);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getContext(), R.anim.fab_rotate_anticlock);

        textview_test = (TextView) view.findViewById(R.id.textview_test);
        textview_ar = (TextView) view.findViewById(R.id.textview_ar);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {

                    textview_test.setVisibility(View.INVISIBLE);
                    textview_ar.setVisibility(View.INVISIBLE);
                    fab2_ar.startAnimation(fab_close);
                    fab1_test.startAnimation(fab_close);
                    fab_main.startAnimation(fab_anticlock);
                    fab2_ar.setClickable(false);
                    fab1_test.setClickable(false);
                    isOpen = false;
                } else {
                    textview_test.setVisibility(View.VISIBLE);
                    textview_ar.setVisibility(View.VISIBLE);
                    fab2_ar.startAnimation(fab_open);
                    fab1_test.startAnimation(fab_open);
                    fab_main.startAnimation(fab_clock);
                    fab2_ar.setClickable(true);
                    fab1_test.setClickable(true);
                    isOpen = true;
                }
            }
        });
        fab2_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onARFabButtonClick();
            }
        });

        fab1_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Take Test", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pdfView = view.findViewById(R.id.coursePdfViewer);
        pdfView.useBestQuality(true);
        pdfView.enableSwipe(true);
        pdfView.fitToWidth();
        pdfView.fromAsset(mPDFName).load();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof CourseContentPdfFragment.onCourseContentPdfFragmentButtonSelected){
            listener = (CourseContentPdfFragment.onCourseContentPdfFragmentButtonSelected) context;
        }else {
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    public interface onCourseContentPdfFragmentButtonSelected{
        public void onARFabButtonClick();
    }
}
