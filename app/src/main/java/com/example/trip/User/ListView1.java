package com.example.trip.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.trip.HelperClasses.HomeAdapter.MostHelperClass;
import com.example.trip.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListView1 extends AppCompatActivity {
    List<LvItem> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL = "http://10.0.2.2:84/Palestine_Info/readPlan.php";
    String JSON_IMAGE_TITLE_NAME = "image_name";
    String JSON_DATE = "date";



    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view1);
        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rec1);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        JSON_DATA_WEB_CALL();

    }

    private void JSON_DATA_WEB_CALL() {
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);

    }

    private void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray response) {

        for(int i = 0; i<response.length(); i++) {

            LvItem GetDataAdapter2 = new LvItem();

            JSONObject json = null;
            try {

                json = response.getJSONObject(i);

                GetDataAdapter2.setTitle(json.getString(JSON_IMAGE_TITLE_NAME));
                GetDataAdapter2.setTime(json.getString(JSON_DATE));



            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerPlan(GetDataAdapter1, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }
    }
