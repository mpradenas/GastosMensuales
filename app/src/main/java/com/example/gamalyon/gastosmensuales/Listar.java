package com.example.gamalyon.gastosmensuales;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import datos.DbHelper;

public class Listar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        listar();
    }

    public void listar()
    {
        DbHelper dbHelp=new DbHelper(this,"db_gastos",null,1);
        SQLiteDatabase db=dbHelp.getReadableDatabase();
        if(db !=null)
        {
            Cursor c=db.rawQuery("SELECT * FROM table_gastos",null);
            int cantidad=c.getCount();
            String[] arreglo=new String[cantidad];
            int i=0;
            if(c.moveToFirst())
            {
                do
                {

                    String linea = "Monto: " +c.getString(0) +
                            "\n\nItem: " + c.getString(1) +
                            "\n\nObservación: " + c.getString(2) +
                            "\n\nFecha: " + c.getString(3) + "\n"; //el numero representa el nimero de la columna.
                    arreglo[i] = linea;
                    i++;

                }while(c.moveToNext());

            }
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arreglo);
            ListView lv=(ListView) findViewById(R.id.Lista);
            lv.setAdapter(arrayAdapter);


        }
    }
}
