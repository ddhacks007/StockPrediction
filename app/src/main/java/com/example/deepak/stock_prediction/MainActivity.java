package com.example.deepak.stock_prediction;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import org.json.JSONObject;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
Button b1;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }
public void onclick(View view){

    url="http://10.0.2.2:8011/direct";
    System.out.println(url);
    requestQueue= Volley.newRequestQueue(this);
    stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("response",       response.toString());
            try{JSONObject jsonObject=new JSONObject(response.toString());
                if(jsonObject.getString("results").equals("sucess".toString())){
                    printfunc(jsonObject.getString("result"));
                }else {
                   }
            }
            catch(Exception e){

            }
        }},new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof NetworkError) {
            } else if (error instanceof ServerError) {
            } else if (error instanceof AuthFailureError) {
            } else if (error instanceof ParseError) {
            } else if (error instanceof NoConnectionError) {
            } else if (error instanceof TimeoutError) {
                Toast.makeText(getApplicationContext(),
                        "Oops. Timeout error!",
                        Toast.LENGTH_LONG).show();
            }
        }
    });

    requestQueue.add(stringRequest);



}
    public void printfunc(String s){

        Toast.makeText(this,s+" is the todays stock price",Toast.LENGTH_LONG).show();
    }
}


