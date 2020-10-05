package com.example.to_do_list_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;


public class AnasayfaActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    ProgressDialog progressDialog;
    LinearLayout linearLayout;
    RequestQueue requestQueue;
    ArrayList<String> yapilacakBaslikfrom;
    ArrayList<String> yapilacakMetinfrom;
    ArrayList<String> yapilacakEpostafrom;
    ArrayList<String> yapilacakOnemfrom;
    ArrayList<String> yapilacakTarihfrom;
    ArrayList<String> yapilacakSaatfrom;
    listeleAdapter listeleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        linearLayout = findViewById(R.id.getir);

        yapilacakBaslikfrom = new ArrayList<>();
        yapilacakMetinfrom = new ArrayList<>();
        yapilacakEpostafrom = new ArrayList<>();
        yapilacakOnemfrom = new ArrayList<>();
        yapilacakTarihfrom = new ArrayList<>();
        yapilacakSaatfrom = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        basla("http://192.168.1.27/todo/goruntu_anasayfa.php");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        RecyclerView recyclerView = findViewById(R.id.anasayfaRecyle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listeleAdapter = new listeleAdapter(yapilacakBaslikfrom,yapilacakMetinfrom,yapilacakEpostafrom,yapilacakOnemfrom,yapilacakTarihfrom,yapilacakSaatfrom);
        recyclerView.setAdapter(listeleAdapter);
       // recyclerView.setHasFixedSize(true);

        floatingActionButton = findViewById(R.id.anasayfaFab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(AnasayfaActivity.this, YapilacakActivity.class));
            }
        });
    }

    private void basla(String URL) {
        final StringRequest sr = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
        };
        AppController.getInstance().addToRequestQueue(sr);
        //requestQueue.add(sr);
    }
}

