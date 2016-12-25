package a07150835.gdmec.com.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by weiruibo on 10/23/16.
 */
public class MyDb extends SQLiteOpenHelper {

    private static String DB_NAME = "My_DB.db";
    private static int DB_VERSION = 2;
    private SQLiteDatabase db;


    public MyDb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase openConnection() {

        if (!db.isOpen()) {

            db = getWritableDatabase();

        }
        return db;
    }


    public void closeConnection() {

        try {
            if (db != null && db.isOpen()) {
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean createTable(String createTableSql) {

        try {
            openConnection();
            db.execSQL(createTableSql);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    public boolean save(String tableName, ContentValues values) {

        try {
            openConnection();
            db.insert(tableName, null, values);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }

        return true;
    }

    public boolean update(String table, ContentValues values, String whereClause, String[] whereArgs) {

        try {
            openConnection();
            db.update(table, values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    public boolean delete(String table, String whereClause, String[] whereArgs) {

        try {
            openConnection();
            db.delete(table, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    public Cursor find(String findSql, String obj[]) {

        try {
            openConnection();
            Cursor cursor = db.rawQuery(findSql, obj);
            return cursor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public boolean isTableExists(String tableName) {

        try {
            openConnection();
            String str = "select count(*) from" + tableName;
            db.rawQuery(str, null).close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }
}
