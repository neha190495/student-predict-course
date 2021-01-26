package com.ibso.student_predict_course;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseMenuFragment extends Fragment {

    private ArrayList<CourseMenuItem> mCourseMenuItemsList;

    private String mCourse;

    private RecyclerView mRecyclerView;
    private CourseMenuAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private onFragmentButtonSelected listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coursemenu, container, false);
        mCourse = getArguments().getString("COURSE");
        //Log.d("COURSE", mCourse);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCourseMenuItemsList = new ArrayList<>();
        String chapter_array_name = mCourse + "_CHAPTERS";
        List<String> list = Arrays.asList(getResources().getStringArray(getResources().getIdentifier(chapter_array_name, "array", getContext().getPackageName())));
        for(String chapter_name : list){
            mCourseMenuItemsList.add(new CourseMenuItem(R.drawable.ic_baseline_menu_book_24, chapter_name));
        }

        mRecyclerView = view.findViewById(R.id.recyclerViewCourseMenu);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CourseMenuAdapter(mCourseMenuItemsList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CourseMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String pdfName = mCourse + "_CHAPTER_" + (position + 1) + ".pdf";
                //Log.d("Position", String.valueOf(position));
                listener.onCourseMenuChapterItemClick(pdfName);
            }
        });
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
        public void onCourseMenuChapterItemClick(String pdfName);
    }
}
