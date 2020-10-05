package com.example.to_do_list_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class GirisActivity extends AppCompatActivity {

    private TextView textView;
    private EditText emailtext;
    private EditText parolatext;
    private Button girisbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        textView=findViewById(R.id.GirisSoru);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GirisActivity.this,KayitActivity.class));
            }
        });
        emailtext  = findViewById(R.id.GirisEmail);
        parolatext = findViewById(R.id.GirisParola);
        girisbutton = findViewById(R.id.GirisButon);

        girisbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kullanici_dogrula("http://192.168.1.27/todo/kullanici_dogrula.php");
            }
        });

    }

    private void kullanici_dogrula(String URL) {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), AnasayfaActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Kullanıcı adı veya şifre yanlış.", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> parametre = new HashMap<>();
                parametre.put("eposta", emailtext.getText().toString());
                parametre.put("sifre",parolatext.getText().toString());
                return  parametre;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}