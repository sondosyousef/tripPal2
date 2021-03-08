package com.example.trip.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trip.R;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ReminderActivity extends AppCompatActivity {

    private FloatingActionButton add;
    private ListView ListV;
    ArrayList<LvItem> arrayList = new ArrayList<>();
    TextView empty;
    String title , t ;

    public ReminderActivity(){

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        ListV=findViewById(R.id.item);
        add= findViewById(R.id.flButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ReminderActivity.this);
                dialog.setContentView(R.layout.activity2_reminder);
                empty = findViewById(R.id.empty);
                final TextView textView = dialog.findViewById(R.id.date);
                final EditText Ed_Title= dialog.findViewById(R.id.message);
                Button selectD = dialog.findViewById(R.id.selectDate);
                Button addPlan = dialog.findViewById(R.id.addButton);
                title = Ed_Title.getText().toString();

                final Calendar newCalender = Calendar.getInstance();


                selectD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog = new DatePickerDialog(ReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {


                                final Calendar newDate = Calendar.getInstance();
                                Calendar newTime = Calendar.getInstance();
                                TimePickerDialog time = new TimePickerDialog(ReminderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                        newDate.set(year,month,dayOfMonth,hourOfDay,minute,0);
                                        Calendar tem = Calendar.getInstance();
                                        Log.w("TIME",System.currentTimeMillis()+"");
                                        if(newDate.getTimeInMillis()-tem.getTimeInMillis()>0)
                                            textView.setText(newDate.getTime().toString());
                                        else
                                            Toast.makeText(ReminderActivity.this,"Invalid time",Toast.LENGTH_SHORT).show();

                                    }
                                },newTime.get(Calendar.HOUR_OF_DAY),newTime.get(Calendar.MINUTE),true);
                                time.show();

                            }
                        },newCalender.get(Calendar.YEAR),newCalender.get(Calendar.MONTH),newCalender.get(Calendar.DAY_OF_MONTH));

                        dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                        dialog.show();

                    }
                });



                addPlan.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(View v) {
                        title = Ed_Title.getText().toString();
                        t=textView.getText().toString();
                        LvItem listI = new LvItem();
                        listI.setTitle(title);
                        listI.setTime(t);
                        arrayList.add(listI);


                        ContectAdapter contectAdapter =new ContectAdapter(arrayList ,ReminderActivity.this);
                        ListV.setAdapter(contectAdapter);



                        Reminders reminders = new Reminders();
                        reminders.setMessage(title.trim());
                        Date remind = new Date(t.trim());
                        reminders.setRemindDate(remind);
                        Log.e("ID chahiye",reminders.getId()+"");

                        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
                        calendar.setTime(remind);
                        calendar.set(Calendar.SECOND,0);
                        Intent intent = new Intent(ReminderActivity.this,NotifierAlarm.class);
                        intent.putExtra("Message",reminders.getMessage());
                        intent.putExtra("RemindDate",reminders.getRemindDate().toString());
                        intent.putExtra("id",reminders.getId());
                        PendingIntent intent1 = PendingIntent.getBroadcast(ReminderActivity.this,reminders.getId(),intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),intent1);

                        Toast.makeText(ReminderActivity.this,"Inserted Successfully",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();





                    }

                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                empty.setVisibility(View.INVISIBLE);


            }
        });





    }
}