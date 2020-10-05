package com.example.to_do_list_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class KayitActivity extends AppCompatActivity {

    private EditText KayitEmail, KayitParola, KayitAd, KayitSoyad;
    private Button kayitButton;
    private TextView kayitsori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        kayitsori = findViewById(R.id.KayitSoru);
        kayitsori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KayitActivity.this, GirisActivity.class));
            }
        });

        KayitEmail  = findViewById(R.id.eposta);
        KayitParola = findViewById(R.id.sifre);
        KayitAd = findViewById(R.id.adi);
        KayitSoyad = findViewById(R.id.soyadi);
        kayitButton = findViewById(R.id.KayitButon);

        kayitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kullanici_ekle("http://192.168.1.27/todo/kullanici_ekle.php");
            }
        });

    }

    private void kullanici_ekle(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Kayıt  işlemi Başarı ile gerçekleşti", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), AnasayfaActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{

                Map<String,String> parametre = new HashMap<String, String>();
                parametre.put("eposta", KayitEmail.getText().toString());
                parametre.put("sifre", KayitParola.getText().toString());
                parametre.put("adi", KayitAd.getText().toString());
                parametre.put("soyadi", KayitSoyad.getText().toString());
                return  parametre;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}