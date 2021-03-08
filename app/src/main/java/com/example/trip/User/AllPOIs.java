package com.example.trip.User;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.trip.HelperClasses.HomeAdapter.FeaturedAdpater;
import com.example.trip.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.trip.HelperClasses.HomeAdapter.MostAdapter;
import com.example.trip.HelperClasses.HomeAdapter.MostHelperClass;
import com.example.trip.R;

public class AllPOIs extends AppCompatActivity {

    List<MostHelperClass> GetDataAdapter1;

    RecyclerView recyclerView;
    RecyclerPoint mAdapter;
    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL = "http://10.0.2.2:84/Palestine_Info/pointOfInt.php";
    String JSON_IMAGE_TITLE_NAME = "image_title";
    String JSON_IMAGE_URL = "image_url";
    String JSON_Description = "description";
    String JSON_Name_City = "city_name";


    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_p_o_is);

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview2);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        JSON_DATA_WEB_CALL();
        EditText search = findViewById(R.id.search2);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filtter(s.toString());

            }
        });


    }

    private void filtter(String text) {
        ArrayList<MostHelperClass> filterItem = new ArrayList<>();
        for(MostHelperClass item :GetDataAdapter1){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filterItem.add(item);
            }

        }
        mAdapter.filterList(filterItem);


    }

    public void JSON_DATA_WEB_CALL(){

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

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            MostHelperClass GetDataAdapter2 = new MostHelperClass();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                GetDataAdapter2.setTitle(json.getString(JSON_IMAGE_TITLE_NAME));
                GetDataAdapter2.setImage(json.getString(JSON_IMAGE_URL));
                GetDataAdapter2.setDescription(json.getString(JSON_Description));
                GetDataAdapter2.setNameCity(json.getString(JSON_Name_City));


            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        mAdapter= new RecyclerPoint(GetDataAdapter1, this);

        recyclerView.setAdapter(mAdapter);
    }
}