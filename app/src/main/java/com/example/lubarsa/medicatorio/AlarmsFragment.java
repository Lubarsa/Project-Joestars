package com.example.lubarsa.medicatorio;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.view.View.OnClickListener;
import android.widget.Toast;

import static android.content.Context.ALARM_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlarmsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlarmsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlarmsFragment extends Fragment implements OnClickListener, AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    int hour;
    int min;
    EditText nameTxt;
    EditText etDosis;
    TextView timesetText;
    Spinner typeSpinner;
    ArrayAdapter<String> typeArray;
    String[] typeOption = new String[]{"Tableta(s)", "Cápsula(s)", "Óvulo(s)", "Pomada", "Jarabe",
    "Crema"};
    TextView measureTxt;


    public String nameNotification;
    public String typeNotification;
    public String measureNotification;

    public static AlarmsFragment instance;




    public AlarmsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlarmsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlarmsFragment newInstance(String param1, String param2) {
        AlarmsFragment fragment = new AlarmsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        instance = this;
        View view = inflater.inflate(R.layout.fragment_alarms,container,false);
        Button timesetButton = (Button) view.findViewById(R.id.timesetBtn);
        nameTxt = (EditText) view.findViewById(R.id.nameTxt);
        typeSpinner = (Spinner) view.findViewById(R.id.typeSpinner);
        typeSpinner.setOnItemSelectedListener(this);
        etDosis = (EditText) view.findViewById(R.id.etDosis);
        typeArray = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,
                typeOption);
        typeSpinner.setAdapter(typeArray);
        measureTxt = (TextView) view.findViewById(R.id.measureTxt);
        Button saveBtn = (Button) view.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
        timesetButton.setOnClickListener(this);
        timesetText = (TextView) view.findViewById(R.id.timesetTxt);
        Log.e("E", "Holi");



        return view;
    }



    @Override
    public void onClick(View view){
        Log.e("E1", "Holi1");
        switch (view.getId()){

            case R.id.timesetBtn:
                Log.e("E2", "Holi2");
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR);
                min = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String stringHours = "" + hourOfDay;
                        String stringMinutes = "" + minute;

                        if(stringHours.length() < 2)
                            stringHours = "0" + stringHours;

                        if(stringMinutes.length() < 2)
                            stringMinutes = "0" + stringMinutes;

                        timesetText.setText(stringHours + ":" + stringMinutes);
                    }

                }, hour, min, false);
                timePickerDialog.show();
                break;
            case R.id.saveBtn:
                Log.e("Error","PruebaAlarma");
                nameNotification = nameTxt.getText().toString();
                measureNotification = measureTxt.getText().toString();
                PendingIntent pendingIntent;

                Date dat = new Date();
                Calendar calendaralarm = Calendar.getInstance();
                Calendar cal_now = Calendar.getInstance();
                cal_now.setTime(dat);
                calendaralarm.setTime(dat);
                calendaralarm.set(Calendar.HOUR_OF_DAY, hour);
                calendaralarm.set(Calendar.MINUTE, min);
                calendaralarm.set(Calendar.SECOND,0);
                if(calendaralarm.before(cal_now)){
                    calendaralarm.add(Calendar.DATE,1);
                }


                Intent myIntent = new Intent(getActivity(), AlarmReceiver.class);
                AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent, 0);
                Log.e("Error","Help! D:");
                Toast.makeText(getActivity(), "Entedemos que quieras recordar a qué hora tomar " + etDosis.getText() + " " +
                        typeNotification + " del medicamento " + nameNotification +
                        " pero eso solo está disponible en la versión PREMIUM :(", Toast.LENGTH_LONG).show();
                manager.set(AlarmManager.RTC_WAKEUP,calendaralarm.getTimeInMillis(), pendingIntent);

                break;
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (typeOption[position]){
            case "Tableta(s)":
                measureTxt.setText("mg");
                break;
            case "Cápsula(s)":
                measureTxt.setText("cápsula(s)");
                break;
            case "Óvulo(s)":
                measureTxt.setText("óvulo(s)");
                break;
            case "Pomada":
                measureTxt.setText("aplicación(es)");
                break;
            case "Jarabe":
                measureTxt.setText("ml");
                break;
            case "Crema":
                measureTxt.setText("aplicación(es)");
                break;
        }

        typeNotification = typeOption[position];


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void OpenTimePicker(View view){

    }






}
