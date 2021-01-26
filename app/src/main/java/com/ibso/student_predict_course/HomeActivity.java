package com.ibso.student_predict_course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        MainFragment.onFragmentBtnSelected,
        PsychometryTestFragment.onFragmentButtonSelected,
        TakePsychometryTest.onTakeTestFragmentButtonSelected,
        CourseMenuFragment.onFragmentButtonSelected,
        CourseContentPdfFragment.onCourseContentPdfFragmentButtonSelected{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Bundle args;
    CourseMenuFragment courseMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.navigationdrawertoolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.navigationdrawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //Load Default Fragment
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, new MainFragment()).addToBackStack(null).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch(item.getItemId()) {
            case R.id.userDetails:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new UserDetailsFragment());
                fragmentTransaction.commit();
                break;
            case R.id.psychometryScore:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new PsychometryTestFragment());
                fragmentTransaction.commit();
                break;
            case R.id.courseScience:
                courseMenuFragment = new CourseMenuFragment();
                args = new Bundle();
                args.putString("COURSE", "SCIENCE");
                courseMenuFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, courseMenuFragment).addToBackStack(null).commit();
                break;
            case R.id.courseEnglish:
                courseMenuFragment = new CourseMenuFragment();
                args = new Bundle();
                args.putString("COURSE", "ENGLISH");
                courseMenuFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, courseMenuFragment).addToBackStack(null).commit();
                break;
            case R.id.courseMath:
                courseMenuFragment = new CourseMenuFragment();
                args = new Bundle();
                args.putString("COURSE", "MATH");
                courseMenuFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, courseMenuFragment).addToBackStack(null).commit();
                break;
            case R.id.courseSocialScience:
                courseMenuFragment = new CourseMenuFragment();
                args = new Bundle();
                args.putString("COURSE", "SOCIALSCIENCE");
                courseMenuFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, courseMenuFragment).addToBackStack(null).commit();
                break;
            default:
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new MainFragment()).addToBackStack(null).commit();
                break;
        }
        return true;
    }

    @Override
    public void onPsychometryTestButtonClick() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new TakePsychometryTest());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onButtonSelected() {

    }

    @Override
    public void onTestConfirmButtonClick() {

    }

    @Override
    public void onCourseMenuChapterItemClick(String pdfName) {
        if(pdfName.equals("SCIENCE_CHAPTER_2.pdf")){
            CourseContentPdfFragment courseContentPdfFragment = new CourseContentPdfFragment();
            args = new Bundle();
            args.putString("PDF_NAME", pdfName);
            courseContentPdfFragment.setArguments(args);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, courseContentPdfFragment).addToBackStack(null).commit();
        }else if(pdfName.equals("SCIENCE_CHAPTER_3.pdf")){
            CourseContentPdfFragment courseContentPdfFragment = new CourseContentPdfFragment();
            args = new Bundle();
            args.putString("PDF_NAME", pdfName);
            courseContentPdfFragment.setArguments(args);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, courseContentPdfFragment).addToBackStack(null).commit();
        }else{
            Toast.makeText(HomeActivity.this, "Content not available for this Chapter", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onARFabButtonClick() {
        DisplayARObjectFragment displayARObjectFragment = new DisplayARObjectFragment();
        //args = new Bundle();
        //args.putString("PDF_NAME", pdfName);
        //courseContentPdfFragment.setArguments(args);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, displayARObjectFragment).addToBackStack(null).commit();
    }
}