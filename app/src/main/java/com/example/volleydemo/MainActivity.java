package com.example.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {


    private static final String URL="developer.github.com/v3/users/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView userList=(RecyclerView) findViewById(R.id.userList);
        userList.setLayoutManager(new LinearLayoutManager(this));

        StringRequest stringRequest=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            //call back k liye ha ye

            public void onResponse(String response) {
                Log.d("DATA",response);

                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();
                User[] users=gson.fromJson(response,User[].class);//response user array type ka ha
                userList.setAdapter(new GithubAdapter(MainActivity.this,users));

            }
        } ,new Response.ErrorListener()
        {

            @Override
            //error k liye ha ye
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
            }
        });
        //cache may data add krti ha  then response krti ha queue may data add krti ha
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);


    }
}
