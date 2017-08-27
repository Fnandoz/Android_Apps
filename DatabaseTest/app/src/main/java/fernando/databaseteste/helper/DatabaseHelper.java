package fernando.databaseteste.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fernando.databaseteste.model.Estados;
import fernando.databaseteste.model.Municipios;

/**
 * Created by Luis Fernando Gomes Sales on 15/01/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DB";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pais";

    private static final String TABELA_ESTADOS = "estados";
    private static final String TABELA_MUNICIPIOS = "municipios";


    // Colunas Estados
    private static final String KEY_ESTADO_ID = "id";
    private static final String KEY_ESTADO_NOME = "nome";
    private static final String KEY_ESTADO_UF = "uf";

    // Colunas Municipios
    private static final String KEY_MUNICIPIOS_ID = "id";
    private static final String KEY_MUNICIPIOS_NOME = "nome";
    private static final String KEY_MUNICIPIOS_UF = "uf_estado";

    private static final String CRIA_TABELA_ESTADOS = "CREATE TABLE "+ TABELA_ESTADOS +" (" +
            KEY_ESTADO_ID +" integer PRIMARY KEY AUTOINCREMENT," +
            KEY_ESTADO_NOME + " text," +
            KEY_ESTADO_UF + " text" +
            ");";

    private static final String CRIA_TABELA_MUNICIPIOS = "CREATE TABLE "+ TABELA_MUNICIPIOS+" (" +
            KEY_MUNICIPIOS_ID + " integer PRIMARY KEY AUTOINCREMENT," +
            KEY_MUNICIPIOS_NOME + " text," +
            KEY_MUNICIPIOS_UF + " text" +
            ");";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIA_TABELA_ESTADOS);
        db.execSQL(CRIA_TABELA_MUNICIPIOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABELA_ESTADOS);
        db.execSQL("DROP TABLE IF EXISTS "+TABELA_MUNICIPIOS);

        onCreate(db);
    }


    // Estados CRUD

    public long criaEstado(Estados estado){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ESTADO_NOME, estado.getNome());
        values.put(KEY_ESTADO_UF, estado.getUf());

        try{
            return db.insert(TABELA_ESTADOS, null, values);

        }catch (Exception e){
            e.printStackTrace();
        }

        return -1;
    }

    public Estados retornaEstado(long id_estado){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABELA_ESTADOS + " WHERE "
                + KEY_ESTADO_ID + " = " + id_estado;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Estados estado = new Estados();

        try{
            estado.setId(c.getInt(c.getColumnIndex(KEY_ESTADO_ID)));
            estado.setNome(c.getString(c.getColumnIndex(KEY_ESTADO_NOME)));
            estado.setUf(c.getString(c.getColumnIndex(KEY_ESTADO_UF)));
        }catch (Exception e){
            e.printStackTrace();
        }

        return c.getCount()==0 ? null:estado;
    }

    public List<Estados> retornaTodosEstados() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Estados> estados = new ArrayList<>();

        String sql = "SELECT * FROM " + TABELA_ESTADOS;
        try {
            Cursor c = db.rawQuery(sql, null);

            if (c != null) {
                if (c.moveToFirst()) {
                    while(!c.isAfterLast()){
                        Estados estadosTemp = new Estados();
                        estadosTemp.setId(c.getInt(c.getColumnIndex(KEY_ESTADO_ID)));
                        estadosTemp.setNome(c.getString(c.getColumnIndex(KEY_ESTADO_NOME)));
                        estadosTemp.setUf(c.getString(c.getColumnIndex(KEY_ESTADO_UF)));
                        estados.add(estadosTemp);
                        c.moveToNext();
                    }
                }
                c.close();
            }
            }catch(Exception e){
                e.printStackTrace();
        }
        return estados;
    }


    public long editaEstado(Estados estado){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ESTADO_NOME, estado.getNome());
        values.put(KEY_ESTADO_UF, estado.getUf());

        try{
            return db.update(TABELA_ESTADOS, values, KEY_ESTADO_ID+"="+estado.getId(), null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }


    public void apagaEstado(Estados estado){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.delete(TABELA_ESTADOS, KEY_ESTADO_ID+"="+estado.getId(), null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // Fim Estados CRUD



    // Municipios CRUD

    public long criaMunicipio(Municipios municipio){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MUNICIPIOS_NOME, municipio.getNome());
        values.put(KEY_MUNICIPIOS_UF, municipio.getUf_estado());

        try{
            return db.insert(TABELA_MUNICIPIOS, null, values);

        }catch (Exception e){
            e.printStackTrace();
        }

        return -1;
    }

    public Municipios retornaMunicipio(long id_municipio){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABELA_MUNICIPIOS + " WHERE "
                + KEY_MUNICIPIOS_ID + " = " + id_municipio;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Municipios municipio = new Municipios();

        try{
            municipio.setId(c.getInt(c.getColumnIndex(KEY_MUNICIPIOS_ID)));
            municipio.setNome(c.getString(c.getColumnIndex(KEY_MUNICIPIOS_NOME)));
            municipio.setUf_estado(c.getString(c.getColumnIndex(KEY_MUNICIPIOS_UF)));
        }catch (Exception e){
            e.printStackTrace();
        }

        return c.getCount()==0 ? null:municipio;
    }

    public Cursor retornaTodosMunicipios(){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABELA_MUNICIPIOS;
        try{
            return db.rawQuery(sql, null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public long editaMunicipios(Municipios m){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MUNICIPIOS_NOME, m.getNome());
        values.put(KEY_MUNICIPIOS_UF, m.getUf_estado());

        try{
            return db.update(TABELA_MUNICIPIOS, values, KEY_MUNICIPIOS_ID+"="+m.getId(), null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }


    public void apagaMunicipios(Municipios m){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.delete(TABELA_MUNICIPIOS, KEY_MUNICIPIOS_ID+"="+m.getId(), null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Fim municipios CRUD


    // Metodos Gerais

    public List<Municipios> municipiosPorEstado(Estados estado){
        ArrayList<Municipios> municipios = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT "+ KEY_MUNICIPIOS_NOME + " FROM " + TABELA_MUNICIPIOS +
                " WHERE " + KEY_MUNICIPIOS_UF + " LIKE" + estado.getUf();

        try{
            Cursor c = db.rawQuery(query, null);

            if(c != null) {
                if (c.moveToFirst()) {
                    do {
                        Municipios municipioTemp = new Municipios();
                        municipioTemp.setId(c.getInt(c.getColumnIndex(KEY_MUNICIPIOS_ID)));
                        municipioTemp.setNome(c.getString(c.getColumnIndex(KEY_MUNICIPIOS_NOME)));
                        municipioTemp.setUf_estado(c.getString(c.getColumnIndex(KEY_MUNICIPIOS_UF)));
                        municipios.add(municipioTemp);

                    } while (c.moveToNext());
                }
                c.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return municipios;
    }
}
