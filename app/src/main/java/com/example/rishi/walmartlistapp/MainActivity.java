package com.example.rishi.walmartlistapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.rishi.walmartlistapp.Model.Product;
import com.example.rishi.walmartlistapp.Presenter.ProductPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductPresenter.ProductPresenterListener {

    private RecyclerView recyclerView;
    public RecyclerAdapter recyclerAdapter;
    private LinearLayoutManager layoutManager;
    private List<Product> items;
    private Context context;
    private ProgressBar progessBar;

    private boolean loading = true;
    int pastvisibleItems, visibleItemCount, totalItem;
    private int page = 1;
    private ProductPresenter productPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(getApplicationContext(),"onCreate",Toast.LENGTH_LONG).show();
        context = this;
        loading = true;


        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        progessBar = (ProgressBar) findViewById(R.id.progress);
        productPresenter = new ProductPresenter(this,this);
        productPresenter.getProducts(page);
        progessBar.setVisibility(View.VISIBLE);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
             //   super.onScrolled(recyclerView, dx, dy);

                if(dy>0){
                  visibleItemCount = layoutManager.getChildCount();
                    totalItem = layoutManager.getItemCount();
                    pastvisibleItems= layoutManager.findFirstVisibleItemPosition();

                    if(loading){

                        if((visibleItemCount + pastvisibleItems) >= totalItem ){
                            loading = false;
                            Log.d("....","Last Item");
                            page++;
                            Log.d("...","Page "+page);
                            productPresenter.getProducts(page);


                        }
                    }

                }
            }
        });
    }


    @Override
    public void productsReady(List<Product> items) {

        if(page == 1) {
            recyclerAdapter = new RecyclerAdapter(getApplicationContext(), items);
            recyclerView.setAdapter(recyclerAdapter);
            progessBar.setVisibility(View.GONE);
        }
        recyclerAdapter.notifyDataSetChanged();
        loading = true;
    }


}
