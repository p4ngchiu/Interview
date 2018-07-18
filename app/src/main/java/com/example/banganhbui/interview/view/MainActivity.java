package com.example.banganhbui.interview.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.banganhbui.interview.R;
import com.example.banganhbui.interview.model.entity.foursquare.Example;
import com.example.banganhbui.interview.presenter.MainPresenter;
import com.example.banganhbui.interview.view.adapter.Adapter;
import com.example.banganhbui.interview.view.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter mainPresenter;
    private RecyclerView listView;
    private EditText edt_reply;
    private ProgressBar progressBar1;
    private ImageButton btnLoad;
    private double longitude;
    private double latitude;
    String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET};
    public static final int MULTIPLE_PERMISSIONS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoad = findViewById(R.id.btn_load_data);
        progressBar1 = findViewById(R.id.progressBar1);
        listView = findViewById(R.id.list_view);
        edt_reply = findViewById(R.id.edt_reply);
        initPresenter();
        checkPermissions();
        getLonLat();
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edt_reply.getText().toString().trim().isEmpty()) {
                    mainPresenter.loadData(longitude, latitude, String.valueOf(edt_reply.getText()));
                    progressBar1.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initPresenter() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void displayMain(Response<Example> listDemo) {
        progressBar1.setVisibility(View.GONE);
        ArrayList<Result> arrayList = new ArrayList<>();
        if (listDemo != null && listDemo.body().getResponse().getGroups().size() != 0 && listDemo.body().getResponse().getGroups().get(0).getItems().size() != 0) {
            arrayList.add(new Result(listDemo.body().getResponse().getGroups().get(0).getItems().get(0).getVenue().getName(), listDemo.body().getResponse().getGroups().get(0).getItems().get(0).getVenue().getLocation().getAddress(), listDemo.body().getResponse().getGroups().get(0).getItems().get(0).getVenue().getLocation().getDistance().toString(), listDemo.body().getResponse().getGroups().get(0).getItems().get(0).getVenue().getUrl()));
        }
        Adapter adapterGallery = new Adapter(getApplication(), arrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapterGallery);
    }

    @Override
    public void displayErr(String err) {

    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    private void getLonLat() {

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }
}
