package com.example.lenskart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lenskart.model.Category;
import com.example.lenskart.model.Filter;
import com.example.lenskart.model.Result;
import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvShape, rvType, rvSize;
    List<Filter> lstShapeCategories = new ArrayList<>();
    List<Filter> lstTYpeCategories = new ArrayList<>();
    List<Filter> lstSizeCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvShape = findViewById(R.id.rv_shape);
        rvType = findViewById(R.id.rv_type);
        rvSize = findViewById(R.id.rv_size);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvShape.setLayoutManager(layoutManager);
        rvType.setLayoutManager(layoutManager);
        rvSize.setLayoutManager(layoutManager);

        String url = "http://api.myjson.com/bins/zfsvs";
        Ion.with(this)
                .load(url)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String response) {
                        if(response !=null && e ==null){
                            Result result = new Gson().fromJson(response, Result.class);
                            lstShapeCategories = result.getCategories().get(0).getFilters();
                            lstTYpeCategories = result.getCategories().get(1).getFilters();
                            lstSizeCategories = result.getCategories().get(2).getFilters();
                        }
                    }
                });

        rvShape.setAdapter(new MainAdapter(lstShapeCategories, new OnClickLisetner() {
            @Override
            public void onClick(int position) {

            }
        }));
    }

    public void addRadioButtons(int number) {
        for (int row = 0; row < 1; row++) {
            RadioGroup ll = new RadioGroup(this);
            ll.setOrientation(LinearLayout.VERTICAL);

            for (int i = 1; i <= number; i++) {
                RadioButton rdbtn = new RadioButton(this);
                rdbtn.setId(View.generateViewId());
                rdbtn.setText("Radio " + rdbtn.getId());
                ll.addView(rdbtn);
            }
            ((ViewGroup) findViewById(R.id.radiogroup)).addView(ll);
        }
    }
}
