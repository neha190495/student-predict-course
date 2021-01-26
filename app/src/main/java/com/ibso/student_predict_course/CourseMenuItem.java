package com.ibso.student_predict_course;

public class CourseMenuItem {

    private int mImageResource;
    private String mText1;

    public CourseMenuItem(int imageResource, String text1){
        mImageResource = imageResource;
        mText1 = text1;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }
}
