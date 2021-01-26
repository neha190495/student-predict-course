package com.ibso.student_predict_course;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseMenuAdapter extends RecyclerView.Adapter<CourseMenuAdapter.CourseMenuViewHolder> {

    private ArrayList<CourseMenuItem> mCourseMenuList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class CourseMenuViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView;

        public CourseMenuViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view_courseitem_icon);
            mTextView = itemView.findViewById(R.id.text_view_courseitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public CourseMenuAdapter(ArrayList<CourseMenuItem> courseMenuList) {
        mCourseMenuList = courseMenuList;
    }

    @NonNull
    @Override
    public CourseMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_menu_item, parent, false);
        CourseMenuViewHolder cmvh = new CourseMenuViewHolder(v, mListener);
        return cmvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseMenuViewHolder holder, int position) {
        CourseMenuItem currentItem = mCourseMenuList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView.setText(currentItem.getText1());
    }

    @Override
    public int getItemCount() {
        return mCourseMenuList.size();
    }
}
