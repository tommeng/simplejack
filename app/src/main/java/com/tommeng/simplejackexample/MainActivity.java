package com.tommeng.simplejackexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tommeng.simplejackexample.adapter.ColorRecyclerAdapter;
import com.tommeng.simplejackexample.model.MyColor;
import com.tommeng.simplejackexample.networking.NetworkManager;
import com.tommeng.simplejackexample.request.BaseRequest;
import com.tommeng.simplejackexample.request.GetRequest;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar tbMain;
    private RecyclerView rvMain;

    private LinearLayoutManager layoutManager;
    private ColorRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbMain = (Toolbar) findViewById(R.id.tbMain);
        rvMain = (RecyclerView) findViewById(R.id.rvMain);

        setSupportActionBar(tbMain);
        tbMain.setTitle(R.string.app_name);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMain.setLayoutManager(layoutManager);

        BaseRequest request = new GetRequest("http://www.colourlovers.com/api/colors", MyColor.class)
                .addQueryParam("format", "json");

        Observable.just(request)
                .map(new Func1<BaseRequest, List<MyColor>>() {
                    @Override
                    public List<MyColor> call(BaseRequest request) {
                        return NetworkManager.get().executeForList(request);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<MyColor>>() {
                    @Override
                    public void call(List<MyColor> myColors) {
                        adapter = new ColorRecyclerAdapter(myColors);
                        rvMain.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
