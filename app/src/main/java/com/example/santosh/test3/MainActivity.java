package com.example.santosh.test3;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == button.getId()) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.106:3000").addConverterFactory(ScalarsConverterFactory.create()).build();
            AppApi appApi = retrofit.create(AppApi.class);

            appApi.hello().enqueue(new Callback<SamplePojo>() {
                @Override
                public void onResponse(Call<SamplePojo> call, Response<SamplePojo> response) {
                    if(response.body() != null) {
                        textView.setText(response.body().getName());
                    }
                }

                @Override
                public void onFailure(Call<SamplePojo> call, Throwable t) {
                    Log.d("Error", "Error in Connection");
                }
            });

        } else if(v.getId() == button2.getId())  {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.106:3000").addConverterFactory(ScalarsConverterFactory.create()).build();
            AppApi appApi = retrofit.create(AppApi.class);
            appApi.hello1().enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("API", response.body());
                    textView.setText(response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("API", t.getMessage());
                }
            });


        }

        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
