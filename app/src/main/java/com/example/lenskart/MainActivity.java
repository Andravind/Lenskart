package com.example.lenskart;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lenskart.model.Category;
import com.example.lenskart.model.ExcludeList;
import com.example.lenskart.model.Filter;
import com.example.lenskart.model.Frame;
import com.example.lenskart.model.Result;
import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvShape, rvType, rvSize;
    HashMap<String, List<Filter>> hashLst= new HashMap<>();
    private MainAdapter shapeAdapter,typeAdapter,sizeAdapter;
    private  String isShapaUpadted="", isTypeUpdated="", isSizeUpdated="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvShape = findViewById(R.id.rv_shape);
        rvType = findViewById(R.id.rv_type);
        rvSize = findViewById(R.id.rv_size);
        rvShape.setLayoutManager(new LinearLayoutManager(this));
        rvType.setLayoutManager(new LinearLayoutManager(this));
        rvSize.setLayoutManager(new LinearLayoutManager(this));

        String url = "http://api.myjson.com/bins/zfsvs";
        shapeAdapter = new MainAdapter(this, new ArrayList<Filter>(), new OnClickLisetner() {
            @Override
            public void onClick(int previousSelected, int position, String filterId, String unSelectable) {
                if (position != -1 && previousSelected != -1) {
                    if (unSelectable != null) {
                        String[] splits = unSelectable.split(",");
                        if(splits[0].equals("2")){
                            isShapaUpadted = "2";
                            List<Filter> lstff = hashLst.get("2");
                            assert lstff != null;
                            for (int i = 0; i < lstff.size(); i++) {
                                if (lstff.get(i).getId().equals(splits[1])) {
                                    lstff.get(i).setSelectable(false);
                                    typeAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }else {
                        if(isShapaUpadted.equals("2")) {
                            isShapaUpadted="";
                            List<Filter> lstff = hashLst.get("2");
                            assert lstff != null;
                            for (int i = 0; i < lstff.size(); i++) {
                                lstff.get(i).setSelectable(true);
                                typeAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                    List<Filter> lstHH = hashLst.get("1");
                    assert lstHH != null;
                    lstHH.get(previousSelected).setDefault(0);
                    lstHH.get(position).setDefault(1);
                    shapeAdapter.notifyDataSetChanged();
                }
            }
        });

        rvShape.setAdapter(shapeAdapter);
        typeAdapter = new MainAdapter(this, new ArrayList<Filter>(), new OnClickLisetner() {
            @Override
            public void onClick(int previousSelected, int position, String filterId, String unSelectable) {
                if (position != -1 && previousSelected != -1) {
                    if (unSelectable != null) {
                        String[] splits = unSelectable.split(",");
                        if (splits[0].equals("1")) {
                            isTypeUpdated = "1";
                            List<Filter> lstff = hashLst.get("1");
                            assert lstff != null;
                            for (int i = 0; i < lstff.size(); i++) {
                                if (lstff.get(i).getId().equals(splits[1])) {
                                    lstff.get(i).setSelectable(false);
                                    shapeAdapter.notifyDataSetChanged();
                                }
                            }
                        } else if (splits[0].equals("3")) {
                            isTypeUpdated = "3";
                            List<Filter> lstff = hashLst.get("3");
                            assert lstff != null;
                            for (int i = 0; i < lstff.size(); i++) {
                                if (lstff.get(i).getId().equals(splits[1])) {
                                    lstff.get(i).setSelectable(false);
                                    sizeAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    } else {
                        if (isTypeUpdated.equals("1")) {
                            isTypeUpdated = "";
                            List<Filter> lstff = hashLst.get("1");
                            assert lstff != null;
                            for (int i = 0; i < lstff.size(); i++) {
                                lstff.get(i).setSelectable(true);
                                shapeAdapter.notifyDataSetChanged();
                            }
                        }
                        if (isTypeUpdated.equals("3")) {
                            isTypeUpdated = "";
                            List<Filter> lstff = hashLst.get("3");
                            assert lstff != null;
                            for (int i = 0; i < lstff.size(); i++) {
                                lstff.get(i).setSelectable(true);
                                sizeAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    List<Filter> lstHH = hashLst.get("2");
                    assert lstHH != null;
                    lstHH.get(previousSelected).setDefault(0);
                    lstHH.get(position).setDefault(1);
                    typeAdapter.notifyDataSetChanged();
                }
            }
        });

        rvType.setAdapter(typeAdapter);

        sizeAdapter = new MainAdapter(this, new ArrayList<Filter>(), new OnClickLisetner() {
            @Override
            public void onClick(int previousSelected, int position, String filterId, String unSelectable) {
                if (position != -1) {
                    if (unSelectable != null) {
                        String[] splits = unSelectable.split(",");
                        if (splits[0].equals("1")) {
                            isSizeUpdated  ="1";
                            List<Filter> lstff = hashLst.get("3");
                            assert lstff != null;
                            for (int i = 0; i < lstff.size(); i++) {
                                if (lstff.get(i).getId().equals(splits[1])) {
                                    lstff.get(i).setSelectable(false);
                                    shapeAdapter.notifyDataSetChanged();
                                }
                            }
                        } else if (splits[0].equals("2")) {
                            isSizeUpdated ="2";
                            List<Filter> lstff = hashLst.get("3");
                            assert lstff != null;
                            for (int i = 0; i < lstff.size(); i++) {
                                if (lstff.get(i).getId().equals(splits[1])) {
                                    lstff.get(i).setSelectable(false);
                                    typeAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }else{
                        if (isSizeUpdated.equals("1")) {
                            isSizeUpdated ="";
                            List<Filter> lstff = hashLst.get("3");
                            assert lstff != null;
                            for (int i = 0; i < lstff.size(); i++) {
                                    lstff.get(i).setSelectable(true);
                                    shapeAdapter.notifyDataSetChanged();

                            }
                        }
                        if (isSizeUpdated.equals("2")) {
                            isSizeUpdated ="";
                            List<Filter> lstff = hashLst.get("3");
                            assert lstff != null;
                            for (int i = 0; i < lstff.size(); i++) {
                                    lstff.get(i).setSelectable(true);
                                    typeAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    List<Filter> lstHH = hashLst.get("3");
                    assert lstHH != null;
                    if (previousSelected != -1)
                        lstHH.get(previousSelected).setDefault(0);
                    lstHH.get(position).setDefault(1);
                    sizeAdapter.notifyDataSetChanged();
                }
            }
        });

        rvSize.setAdapter(sizeAdapter);

        Ion.with(this)
                .load(url)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onCompleted(Exception e, String response) {
                        if (response != null && e == null) {
                            Frame result = new Gson().fromJson(response, Frame.class);
                            for(int i=0; i<result.getResult().getCategories().size();i++){
                                hashLst.put(result.getResult().getCategories().get(i).getCategoryId(), result.getResult().getCategories().get(i).getFilters());
                            }
                            for(int j=0; j<result.getResult().getExcludeList().size(); j++){
                                    if(hashLst.containsKey(result.getResult().getExcludeList().get(j).get(0).getCategoryId())){
                                     List<Filter> lstUpdate = hashLst.get(result.getResult().getExcludeList().get(j).get(0).getCategoryId());
                                        for(int h=0; h<lstUpdate.size(); h++) {
                                            if (lstUpdate.get(h).getId().equals(result.getResult().getExcludeList().get(j).get(0).getFilterId())) {
                                                lstUpdate.get(h).setUnSelectableId(result.getResult().getExcludeList().get(j).get(1).getCategoryId() + "," + result.getResult().getExcludeList().get(j).get(1).getFilterId());
                                                break;
                                            }
                                        }
                                        hashLst.replace(result.getResult().getExcludeList().get(j).get(0).getCategoryId(),lstUpdate);
                                    }
                                    if(hashLst.containsKey(result.getResult().getExcludeList().get(j).get(1).getCategoryId())) {
                                        List<Filter> lstUpdate1 = hashLst.get(result.getResult().getExcludeList().get(j).get(1).getCategoryId());
                                        for (int h = 0; h < lstUpdate1.size(); h++) {
                                            if (lstUpdate1.get(h).getId().equals(result.getResult().getExcludeList().get(j).get(1).getFilterId())) {
                                                lstUpdate1.get(h).setUnSelectableId(result.getResult().getExcludeList().get(j).get(0).getCategoryId() + "," + result.getResult().getExcludeList().get(j).get(0).getFilterId());
                                                break;
                                            }
                                        }
                                        hashLst.replace(result.getResult().getExcludeList().get(j).get(1).getCategoryId(),lstUpdate1);
                                    }

                            }
                            Log.e("hashLst", hashLst.toString() + "Check");

                        /*    lstShapeCategories = result.getResult().getCategories().get(0).getFilters();
                            lstTYpeCategories = result.getResult().getCategories().get(1).getFilters();
                            lstSizeCategories = result.getResult().getCategories().get(2).getFilters();
                            lstExcludedLst = result.getResult().getExcludeList();
*/
                            shapeAdapter.setData(hashLst.get("1"));
                            typeAdapter.setData(hashLst.get("2"));
                            sizeAdapter.setData(hashLst.get("3"));
                        }
                    }
                });
    }

   /* public void addRadioButtons(int number) {
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
    }*/
}
