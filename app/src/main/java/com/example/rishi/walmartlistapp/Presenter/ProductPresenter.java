package com.example.rishi.walmartlistapp.Presenter;

import android.content.Context;

import com.example.rishi.walmartlistapp.Model.Product;
import com.example.rishi.walmartlistapp.Model.SetOfItems;
import com.example.rishi.walmartlistapp.Service.ItemService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rishi on 9/7/2017.
 */

public class ProductPresenter {

    private final Context context;
    private final ProductPresenterListener mListener;
    private final ItemService itemService;
    private List<Product> list;

    public interface ProductPresenterListener{
        void productsReady(List<Product> items );
    }

    public ProductPresenter(ProductPresenterListener listener, Context context){
        this.mListener = listener;
        this.context = context;
        this.itemService = new ItemService();
        this.list = new ArrayList<Product>();
    }

    public void getProducts(int page){
        itemService
                .getApi()
                .getResults(page)
                .enqueue(new Callback<SetOfItems>() {
                    @Override
                    public void onResponse(Call<SetOfItems> call, Response<SetOfItems> response) {
                        SetOfItems result =response.body();

                        if(result!=null){
                            list.addAll(result.getProducts());
                          //  mListener.productsReady(result.getProducts());
                            mListener.productsReady(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<SetOfItems> call, Throwable t) {

                        try {
                            throw new InterruptedException("An error occured while communicating with server");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}
