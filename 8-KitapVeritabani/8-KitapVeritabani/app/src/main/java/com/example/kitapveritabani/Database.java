package com.example.kitapveritabani;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_kitap";
    private static final String TABLE_NAME = "tbl_kitap";

    private static String KITAP_KODU = "kitap_kodu";
    private static String KITAP_ADI = "kitap_adı";
    private static String KITAP_YAZARI = "kitap_yazarı";
    private static String KITAP_BASIM_YILI = "kitap_basım_yılı";
    private static String KITAP_FIYATI = "kitap_fiyat";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KITAP_KODU + " INTEGER PRIMARY KEY,"
                + KITAP_ADI + " TEXT,"
                + KITAP_YAZARI + " TEXT,"
                + KITAP_BASIM_YILI + " TEXT,"
                + KITAP_FIYATI + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    //----------------------------------------KİTAP EKLE--------------------------------------------
    public void kitapEkle(String kitap_kodu, String kitap_adı, String kitap_yazarı, String kitap_basım_yılı,
                          String kitap_fiyatı) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KITAP_KODU, kitap_kodu);
        values.put(KITAP_ADI, kitap_adı);
        values.put(KITAP_YAZARI, kitap_yazarı);
        values.put(KITAP_BASIM_YILI, kitap_basım_yılı);
        values.put(KITAP_FIYATI, kitap_fiyatı);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //----------------------------------------KİTAP SİL---------------------------------------------
    public void kitapSil(int kod) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KITAP_KODU + " = ?", new String[] { String.valueOf(kod) });
        db.close();
    }

    //----------------------------------------KİTAP DÜZENLE-----------------------------------------
    public void kitapDüzenle(String kitap_kodu, String kitap_adi, String kitap_yazari, String kitap_basim_yili,
                             String kitap_fiyat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KITAP_KODU, kitap_kodu);
        values.put(KITAP_ADI, kitap_adi);
        values.put(KITAP_YAZARI, kitap_yazari);
        values.put(KITAP_BASIM_YILI, kitap_basim_yili);
        values.put(KITAP_FIYATI, kitap_fiyat);

        db.update(TABLE_NAME, values, KITAP_KODU + " = ?", new String[] { String.valueOf(kitap_kodu) });
    }

    //----------------------------------------KİTAP ARAMA-----------------------------------------
    public HashMap<String,String> kitapDetay(int kod) {

        HashMap <String, String> kitap = new HashMap<String, String>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME+ " WHERE kitap_kodu=" + kod;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            kitap.put(KITAP_KODU, cursor.getString(0));
            kitap.put(KITAP_ADI, cursor.getString(1));
            kitap.put(KITAP_YAZARI, cursor.getString(2));
            kitap.put(KITAP_BASIM_YILI, cursor.getString(3));
            kitap.put(KITAP_FIYATI, cursor.getString(4));
        }
        cursor.close();
        db.close();
        return kitap;
    }

    public ArrayList < HashMap < String, String > > kitaplar() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> kitaplist = new ArrayList< HashMap<String, String>>() ;

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount();i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }
                kitaplist.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        return kitaplist;
    }

    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        return rowCount;
    }

    public void resetTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO Otomatik oluşturuldu.
    }
}