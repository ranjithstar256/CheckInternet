package com.androidmanifester.checkinternet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
//import com.github.paolorotolo.appintro.model.SliderPage;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("What this App do?", "Even though it shows you are connected to internet. " +
                        "but when there's no connectivity you should open a browser and check whether there's internet is working or not!" +
                        "This is a annoying problem to find out whether internet is working or not!"
                +"This App helps you to immediately find out if the internet is not available."
                ,R.drawable.imag, ContextCompat.getColor(getApplicationContext(), R.color.slide1)));

        addSlide(AppIntroFragment.newInstance("Green and Red Widget", "If you have internet green button widget will be shown. If the internet become unavailable red button widget will be shown"
                ,R.drawable.green, ContextCompat.getColor(getApplicationContext(),  R.color.slide2)));

        addSlide(AppIntroFragment.newInstance("Start Service Button", "It starts the floating widget and it is movable."
                ,R.drawable.buttonshape, ContextCompat.getColor(getApplicationContext(), R.color.slide3)));


        addSlide(AppIntroFragment.newInstance("Stop Service Button", "It closes all the background service and stop the process."
                , R.drawable.buttonshape, ContextCompat.getColor(getApplicationContext(), R.color.slide5)));

        addSlide(AppIntroFragment.newInstance("Close Button", "It is a shortcut to stop the App Service"
                ,R.drawable.close, ContextCompat.getColor(getApplicationContext(), R.color.slide4)));

        setFadeAnimation();
    }
    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }
}
