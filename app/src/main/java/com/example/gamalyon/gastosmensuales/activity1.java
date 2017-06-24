package com.example.gamalyon.gastosmensuales;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import datos.DbHelper;

public class activity1 extends AppCompatActivity {

    Spinner sp;
    DatePicker calendario;
    Date fecha;
    ArrayAdapter arrayAdapter;
    String[]gastos;
    EditText edt1,edt2;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);
        sp=(Spinner) findViewById(R.id.spinner);
        edt1=(EditText) findViewById(R.id.editText);
        edt2=(EditText) findViewById(R.id.editText2);
        calendario=(DatePicker)findViewById(R.id.datePicker);

        gastos=new String[]{"selecciona","cuentas","supermercado","tiendas","vestuario"};
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,gastos);
        sp.setAdapter(arrayAdapter);
    }

    public void guardar(View view) throws ParseException {
        if(TextUtils.isEmpty(edt1.getText()) || TextUtils.isEmpty(edt2.getText()))
        {
            Toast.makeText(this, "No dejes ningún campo sin datos", Toast.LENGTH_SHORT).show();
        }
        else if(sp.getSelectedItemPosition()==0)
        {
            Toast.makeText(this, "Selecciona el gasto", Toast.LENGTH_SHORT).show();
        }
        else
        {

            SimpleDateFormat formater=new SimpleDateFormat("mm-dd-yyyy");
            Date Lafecha=formater.parse(calendario.getDayOfMonth()+"-"+calendario.getMonth()+"-"+calendario.getYear());
            String fecha=calendario.getDayOfMonth()+"-"+calendario.getMonth()+"-"+calendario.getYear();
            double monto=Double.parseDouble(edt1.getText().toString());
            String descripcion=edt2.getText().toString();
            String gasto=sp.getSelectedItem().toString();
            DbHelper dbhelp= new DbHelper(view.getContext(),"db_gastos",null,1);
            SQLiteDatabase db=dbhelp.getWritableDatabase();
            if(db!=null){
                ContentValues gastosRegistro=new ContentValues();
                gastosRegistro.put("monto",monto);
                gastosRegistro.put("gasto",gasto);
                gastosRegistro.put("descripcion",descripcion);
                gastosRegistro.put("fecha",fecha);

                long i=db.insert("table_gastos",null,gastosRegistro);
                if(i>0)
                {
                    Toast.makeText(this, "Se ha insertado registro", Toast.LENGTH_SHORT).show();
                }
                else 
                {
                    Toast.makeText(this, "No se ha insertado registro", Toast.LENGTH_SHORT).show();
                }

            }


        }

    }


}
