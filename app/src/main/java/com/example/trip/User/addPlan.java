package com.example.trip.User;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.trip.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class addPlan extends AppCompatActivity {
    private ListView ListV;
    TextView textView;
    ArrayList<LvItem> arrayList = new ArrayList<>();
    TextView empty;
    String image_name, date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        empty = findViewById(R.id.name);
        textView = findViewById(R.id.date);
        Button selectD = findViewById(R.id.selectDate);
        Button addPlan = findViewById(R.id.addButton);
        String imageName = getIntent().getStringExtra("data");
        empty.setText(imageName);
        image_name = empty.getText().toString();
        date = textView.getText().toString();


        final Calendar newCalender = Calendar.getInstance();
        selectD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(addPlan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {


                        final Calendar newDate = Calendar.getInstance();
                        Calendar newTime = Calendar.getInstance();
                        TimePickerDialog time = new TimePickerDialog(addPlan.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                newDate.set(year, month, dayOfMonth, hourOfDay, minute, 0);
                                Calendar tem = Calendar.getInstance();
                                Log.w("TIME", System.currentTimeMillis() + "");
                                if (newDate.getTimeInMillis() - tem.getTimeInMillis() > 0)
                                    textView.setText(newDate.getTime().toString());
                                else
                                    Toast.makeText(addPlan.this, "Invalid time", Toast.LENGTH_SHORT).show();

                            }
                        }, newTime.get(Calendar.HOUR_OF_DAY), newTime.get(Calendar.MINUTE), true);
                        time.show();

                    }
                }, newCalender.get(Calendar.YEAR), newCalender.get(Calendar.MONTH), newCalender.get(Calendar.DAY_OF_MONTH));

                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();

            }
        });


        addPlan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                image_name = empty.getText().toString();
                  date = textView.getText().toString();
                // LvItem listI = new LvItem();
                //  listI.setTitle(title);
                //  listI.setTime(t);
                //  arrayList.add(listI);
                String restUrl = "http://10.0.2.2:84/Palestine_Info/addPlan.php";
                if (ContextCompat.checkSelfPermission(addPlan.this,
                        Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(addPlan.this,
                            new String[]{Manifest.permission.INTERNET},
                            123);

                } else {
                    SendPostRequest runner = new SendPostRequest();
                    runner.execute(restUrl);
                }


                Intent intent = new Intent(addPlan.this, ListView1.class);
                // intent.putExtra("Message",listI.getTitle());
                //  intent.putExtra("RemindDate",listI.getTime());
                startActivity(intent);
            }
        });
    }





    private String processRequest(String restUrl) throws UnsupportedEncodingException {
        image_name = empty.getText().toString();
        date = textView.getText().toString();


        String data = URLEncoder.encode("image_name", "UTF-8")
                + "=" + URLEncoder.encode(image_name, "UTF-8");

        data += "&" + URLEncoder.encode("date", "UTF-8") + "="
                + URLEncoder.encode(date, "UTF-8");


        String text = "";
        BufferedReader reader = null;

        // Send data
        try {

            // Defined URL  where to send data
            URL url = new URL(restUrl);

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // Show response on activity
        return text;


    }

    private class SendPostRequest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return processRequest(urls[0]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "";
        }
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(addPlan.this, result, Toast.LENGTH_SHORT).show();
        }
    }

}