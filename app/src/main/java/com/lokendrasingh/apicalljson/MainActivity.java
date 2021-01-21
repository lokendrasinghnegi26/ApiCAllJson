package com.lokendrasingh.apicalljson;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewresult= findViewById(R.id.text_view_result);

        // below we will execute our get request for this we have need an instance of Retrofit
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholder jsonPlaceholder= retrofit.create(JsonPlaceholder.class);
        Call<List<Post>> call= jsonPlaceholder.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful())
                {
                    textViewresult.setText("code:" +response.code());
                    return;

                }
                List<Post> posts= response.body();
                for(Post post: posts)
                {
                    String content="";
                    content += "ID:"+ post.getId();
                    content += "User ID:"+ post.getUserId();
                    content += "title:"+ post.getTitle();
                    content += "Text:"+ post.getText();
                    textViewresult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewresult.setText(t.getMessage());

            }
        });


    }
}