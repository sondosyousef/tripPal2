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
import com.example.trip.R;

public class AllCities extends AppCompatActivity {

    List<FeaturedHelperClass> GetDataAdapter1;

    RecyclerView recyclerView;
    RecycleCity mAdapter;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL = "http://10.0.2.2:84/Palestine_Info/citiesPage.php";
    String JSON_IMAGE_TITLE_NAME = "image_title";
    String JSON_IMAGE_URL = "image_url";
    String JSON_Description = "description";
    String JSON_point1 = "point_1";
    String JSON_point2 = "point_2";
    String JSON_point3 = "point_3";
    String JSON_point4 = "point_4";
    String JSON_img_point1 = "img_point1";
    String JSON_img_point2 = "img_point2";
    String JSON_img_point3 = "img_point3";
    String JSON_img_point4 = "img_point4";
    String JSON_DES1= "des1";
    String JSON_DES2 = "des2";
    String JSON_DES3 = "des3";
    String JSON_DES4 = "des4";

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_cities);

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        JSON_DATA_WEB_CALL();
        EditText search = findViewById(R.id.search);
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
        ArrayList<FeaturedHelperClass> filterItem = new ArrayList<>();
        for(FeaturedHelperClass item :GetDataAdapter1){
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

            FeaturedHelperClass GetDataAdapter2 = new FeaturedHelperClass();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                GetDataAdapter2.setTitle(json.getString(JSON_IMAGE_TITLE_NAME));

                GetDataAdapter2.setImage(json.getString(JSON_IMAGE_URL));
                GetDataAdapter2.setDescription(json.getString(JSON_Description));
                GetDataAdapter2.setPoin1(json.getString(JSON_point1));
                GetDataAdapter2.setPoint2(json.getString(JSON_point2));
                GetDataAdapter2.setPoint3(json.getString(JSON_point3));
                GetDataAdapter2.setPoint4(json.getString(JSON_point4));
                GetDataAdapter2.setImgPoint1(json.getString(JSON_img_point1));
                GetDataAdapter2.setImgPoint2(json.getString(JSON_img_point2));
                GetDataAdapter2.setImgPoint3(json.getString(JSON_img_point3));
                GetDataAdapter2.setImgPoint4(json.getString(JSON_img_point4));
                GetDataAdapter2.setDes1(json.getString(JSON_DES1));
                GetDataAdapter2.setDes2(json.getString(JSON_DES2));
                GetDataAdapter2.setDes3(json.getString(JSON_DES3));
                GetDataAdapter2.setDes4(json.getString(JSON_DES4));


            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        mAdapter = new RecycleCity(GetDataAdapter1, this);

        recyclerView.setAdapter(mAdapter);

    }

}