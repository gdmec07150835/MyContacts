package a07150835.gdmec.com.mycontacts;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by weiruibo on 10/23/16.
 */
public class ContactsTable {

    private final static String TABLENAME = "contactsTable";

    private MyDb db;

    public ContactsTable(Context context) {

        db = new MyDb(context);

        if (!db.isTableExists(TABLENAME)) {

            String createTableSql = "create table if not exists " + TABLENAME + "(id_DB integer primary key autoincrement," +
                    User.NAME + " varchar not null," +
                    User.MOBILE + " varchar not null," +
                    User.QQ + " varchar not null," +
                    User.COMPANY + " varchar not null," +
                    User.ADDRESS + " varchar not null)";
            db.createTable(createTableSql);

        }
    }

    public boolean addData(User user) {

        ContentValues values = new ContentValues();

        values.put(User.NAME, user.getName());
        values.put(User.MOBILE, user.getMobile());
        values.put(User.COMPANY, user.getCompany());
        values.put(User.QQ, user.getQq());
        values.put(User.ADDRESS, user.getAddress());


        return db.save(TABLENAME, values);

    }
}
