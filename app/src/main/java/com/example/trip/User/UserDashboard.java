package com.example.trip.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.trip.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.trip.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.trip.HelperClasses.HomeAdapter.FeaturedAdpater;
import com.example.trip.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.trip.HelperClasses.HomeAdapter.MostAdapter;
import com.example.trip.HelperClasses.HomeAdapter.MostHelperClass;
import com.example.trip.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    //Variable
    static final float END_SCALE = 0.7f;
    RecyclerView featuredRecycler, mostViewRecycler, categoriesViewRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    ImageView menuIcon;
    LinearLayout contentView;
    Button mapBtn;
    List<FeaturedHelperClass> GetDataAdapter1;
    List<MostHelperClass> GetMostAdapter2;
    FeaturedAdpater mAdapter;
    MostAdapter mAdapter1;
    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager,recyclerViewlayoutManager2;

    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView.Adapter recyclerViewadapter2;

    String GET_JSON_DATA_HTTP_URL = "http://10.0.2.2:84/Palestine_Info/citiesPage.php";
    String GET_JSON_DATA_HTTP_URL2 = "http://10.0.2.2:84/Palestine_Info/pointOfInt.php";

    String JSON_IMAGE_TITLE_NAME = "image_title";
    String JSON_IMAGE_URL = "image_url";
    String JSON_Description = "description";
    String JSON_Name_City = "city_name";

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

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView( R.layout.activity_user_dashboard );
        //Hooks

        mostViewRecycler = findViewById( R.id.most_recycler );
       // categoriesViewRecycler = findViewById( R.id.categories_recycler );
        menuIcon = findViewById( R.id.menu_icon );
        contentView = findViewById( R.id.content );
        GetDataAdapter1 = new ArrayList<>();
        GetMostAdapter2 = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.featured_recycler);

        recyclerView.setHasFixedSize(true);
        mostViewRecycler.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewlayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        mostViewRecycler.setLayoutManager(recyclerViewlayoutManager2);

        JSON_DATA_WEB_CALL();
        JSON_DATA_WEB_CALL2();


       // mapBtn = findViewById( R.id.map_id );

        //Menu Hooks
        drawerLayout = findViewById( R.id.drawer_layout );
        navigationView = findViewById( R.id.navigation_view );


        navigationDrawer();


        //Recycler Views Function Calls
        EditText search = findViewById(R.id.mainSearch);
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
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s1) {
                filtter1(s1.toString());

            }
        });


    }

    private void filtter1(String text) {
        ArrayList<MostHelperClass> filterItem = new ArrayList<>();
        for(MostHelperClass item :GetMostAdapter2){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filterItem.add(item);
            }

        }
        mAdapter1.filterList(filterItem);


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
    public void JSON_DATA_WEB_CALL2(){

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL2,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL2(response);
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



    //Nnavigation Drawer Function
    private void navigationDrawer() {
        //Naviagtion Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener( this );
        navigationView.setCheckedItem( R.id.nav_home );

        menuIcon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible( GravityCompat.START ))
                    drawerLayout.closeDrawer( GravityCompat.START );
                else drawerLayout.openDrawer( GravityCompat.START );

            }
        } );
        animateNaviagtionDrawer();
    }

    private void animateNaviagtionDrawer() {

       // drawerLayout.setScrimColor( getResources().getColor( R.color.colorAccent ) );
        drawerLayout.addDrawerListener( new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                //Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;

                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                //Translate the View, accounting for the scaled width

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2 ;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }


        }
        );
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible( GravityCompat.START )){
            drawerLayout.closeDrawer( GravityCompat.START );
        }else
        super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                startActivity( new Intent(getApplicationContext(),UserDashboard.class));
                break;
            case R.id.nav_all_cities:
                startActivity( new Intent(getApplicationContext(),AllCities.class));
                break;
            case R.id.nav_all_pois:
                startActivity( new Intent(getApplicationContext(),AllPOIs.class));
                break;
           // case R.id.nav_all_categories:
            //    startActivity( new Intent(getApplicationContext(),AllCategories.class));
            //    break;
            case R.id.nav_map:startActivity( new Intent( this, MapsActivity2.class ) );
                break;
            case R.id.nav_track:
                startActivity( new Intent( this, MapActivity.class ) );
                break;
            case R.id.nav_add_tourplan:
                startActivity( new Intent( this, ListView1.class ) );
                break;
            case R.id.nav_reminder:
                startActivity( new Intent( this, ReminderActivity.class ) );
                break;







        }
        return true;

    }





    private void categoriesViewRecycler() {
        categoriesViewRecycler.setHasFixedSize( true );
        categoriesViewRecycler.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false ) );
        ArrayList<CategoriesHelperClass> categoriesView = new ArrayList<>();
        categoriesView.add( new CategoriesHelperClass( R.drawable.add_missing_place, "Hotales" ) );
        categoriesView.add( new CategoriesHelperClass( R.drawable.sit_back_and_relax, "Car Rntal" ) );
        categoriesView.add( new CategoriesHelperClass( R.drawable.search_place, "Jerusalem" ) );
        categoriesView.add( new CategoriesHelperClass( R.drawable.add_missing_place, "Acr" ) );
        categoriesView.add( new CategoriesHelperClass( R.drawable.add_missing_place, "Sondos" ) );

        adapter = new CategoriesAdapter( categoriesView );
        categoriesViewRecycler.setAdapter( adapter );
        GradientDrawable gradient2 = new GradientDrawable( GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600} );
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

        mAdapter = new FeaturedAdpater(GetDataAdapter1, this);

        recyclerView.setAdapter(mAdapter);
    }
    public void JSON_PARSE_DATA_AFTER_WEBCALL2(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            MostHelperClass mostHelperClass = new MostHelperClass();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                mostHelperClass.setTitle(json.getString(JSON_IMAGE_TITLE_NAME));

                mostHelperClass.setImage(json.getString(JSON_IMAGE_URL));
                mostHelperClass.setDescription(json.getString(JSON_Description));
                mostHelperClass.setNameCity(json.getString(JSON_Name_City));




            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetMostAdapter2.add(mostHelperClass);
        }

        mAdapter1 = new MostAdapter(GetMostAdapter2, this);

        mostViewRecycler.setAdapter(mAdapter1);
    }







    public void mappage(View view) {
        startActivity( new Intent( this, MapActivity.class ) );
        finish();
    }

    public void reminder(View view) {
        startActivity( new Intent( this, ReminderActivity.class ) );
        finish();
    }

    public void mylocation(View view) {
        startActivity( new Intent( this, MylocationActivity.class ) );
        finish();
    }

    public void tourplan(View view) {
        startActivity( new Intent( this, ListView1.class ) );
        finish();
    }


}