package com.example.lubarsa.medicatorio;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;

public class ContainerActivity extends AppCompatActivity
    implements UserCareFragment.OnFragmentInteractionListener {

    String buttonPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        if(findViewById(R.id.FragmentLayoutContainer) != null ){

            if(savedInstanceState != null){
                return;
            }

            UserCareFragment firstFragment = new UserCareFragment();

            firstFragment.setArguments(getIntent().getExtras());

          //  getSupportFragmentManager().beginTransaction().add(R.id.FragmentLayoutContainer,
            //        firstFragment).commit();

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.FragmentLayoutContainer, firstFragment, "First");
            buttonPosition = "First";
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void ChangeToAlarmScreen(View view) {
        AlarmsFragment newAlarmsFragment = new AlarmsFragment();


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.FragmentLayoutContainer, newAlarmsFragment, "Second");
        Fragment fragmentDelete = getFragmentManager().findFragmentByTag(buttonPosition);
        fragmentTransaction.remove(fragmentDelete);
        buttonPosition = "Second";
        fragmentTransaction.commit();
    }

    public void ChangeToCareScreen(View view) {

        UserCareFragment newCareFragment = new UserCareFragment();


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.FragmentLayoutContainer, newCareFragment, "First");
        Fragment fragmentDelete = getFragmentManager().findFragmentByTag(buttonPosition);
        fragmentTransaction.remove(fragmentDelete);
        buttonPosition = "First";
        fragmentTransaction.commit();
    }
}
