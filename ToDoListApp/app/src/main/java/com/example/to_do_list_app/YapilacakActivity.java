package com.example.to_do_list_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class YapilacakActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] onemler = {"Az", "Orta", "Fazla"};
    String eo = "";

    Spinner spinner;
    DatePickerDialog dataPickerDialog;
    TimePickerDialog timePickerDialog;
    EditText eBaslik, eMetin, eEposta, eOnem, eTarih, eSaat;
    Button kaydet, iptal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yapilacak);
        eBaslik = findViewById(R.id.baslik);
        eMetin = findViewById(R.id.metin);
        eEposta = findViewById(R.id.eposta);

        spinner = findViewById(R.id.onem);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,onemler);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        eTarih = findViewById(R.id.tarih);
        eTarih.setInputType(InputType.TYPE_NULL);
        eTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int yil = calendar.get(Calendar.YEAR);
                int ay = calendar.get(Calendar.MONTH);
                int gun = calendar.get(Calendar.DAY_OF_MONTH);
                dataPickerDialog = new DatePickerDialog(YapilacakActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       eTarih.setText(year  + "/" + (month + 1) + "/" +dayOfMonth );
                    }
                },yil,ay,gun);
                dataPickerDialog.show();
            }
        });

        eSaat = findViewById(R.id.saat);
        eSaat.setInputType(InputType.TYPE_NULL);
        eSaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int saat = calendar.get(Calendar.HOUR_OF_DAY);
                int dakika = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(YapilacakActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        eSaat.setText(hourOfDay + ":" + minute);
                    }
                }, saat, dakika, true);
                timePickerDialog.show();
            }
        });

        kaydet = findViewById(R.id.kaydet);
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yapilacak_ekle("http://192.168.1.27/todo/yapilacak_ekle.php");
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Onem : " + onemler[position] , Toast.LENGTH_SHORT).show();
        parent.getItemAtPosition(position);
        eo = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void yapilacak_ekle(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Yapilacaklar işlemi Başarı ile Kaydedildi", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), AnasayfaActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametre = new HashMap<String, String>();
                parametre.put("baslik", eBaslik.getText().toString());
                parametre.put("metin", eMetin.getText().toString());
                parametre.put("eposta", eEposta.getText().toString());
                parametre.put("onem", eo);
                parametre.put("tarih", eTarih.getText().toString());
                parametre.put("saat", eSaat.getText().toString());
                return  parametre;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}