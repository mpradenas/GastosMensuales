package datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gamalyon on 16/06/2017.
 */

public class DbHelper extends SQLiteOpenHelper{

    String sql="create table table_gastos(monto TEXT,gasto TEXT,descripcion TEXT,fecha TEXT)";
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
