package com.example.vidhyaniketan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class ContactDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactDB";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_MESSAGE = "message";

    // Create table query
    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_PHONE + " TEXT, "
            + COLUMN_MESSAGE + " TEXT)";

    public ContactDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    // Method to get all contacts from the database
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        // Get readable database instance
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] columns = {
                COLUMN_NAME,
                COLUMN_EMAIL,
                COLUMN_PHONE,
                COLUMN_MESSAGE
        };

        try {
            // Query the database
            Cursor cursor = db.query(
                    TABLE_CONTACTS,    // Table name
                    columns,          // Columns to return
                    null,            // Where clause
                    null,            // Where clause arguments
                    null,            // Group by
                    null,            // Having
                    COLUMN_NAME + " ASC"  // Order by name ascending
            );

            // Iterate through all rows and add to list
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact(
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MESSAGE))
                    );
                    contactList.add(contact);
                } while (cursor.moveToNext());

                // Close the cursor
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database
            db.close();
        }

        return contactList;
    }

    // Method to add a new contact
    public long addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_PHONE, contact.getPhone());
        values.put(COLUMN_MESSAGE, contact.getMessage());

        // Insert row
        long id = db.insert(TABLE_CONTACTS, null, values);

        // Close the database
        db.close();

        return id;
    }

    // Method to delete a contact
    public void deleteContact(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, COLUMN_NAME + " = ?", new String[]{name});
        db.close();
    }

    // Method to update a contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_PHONE, contact.getPhone());
        values.put(COLUMN_MESSAGE, contact.getMessage());

        // Update row
        int rowsAffected = db.update(TABLE_CONTACTS,
                values,
                COLUMN_NAME + " = ?",
                new String[]{contact.getName()});

        db.close();
        return rowsAffected;
    }

    // Method to get contact count
    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }
}