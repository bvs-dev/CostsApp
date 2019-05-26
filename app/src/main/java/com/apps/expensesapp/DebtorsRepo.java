package com.apps.expensesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DebtorsRepo extends SQLiteOpenHelper {

    public interface Listener {

        void onDataChanged();

    }

    private static DebtorsRepo sInstance;

    public static DebtorsRepo getInstance(Context context) {
        if (sInstance == null) sInstance = new DebtorsRepo(context.getApplicationContext());
        return sInstance;
    }

    private static final String TABLE_NAME = "Debtors";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String USER_DEBT_VAL = "user_debt_val";
    private static final String DEBT_VAL = "debt_val";
    private static final String DB_NAME = "debtors.db";
    private static final int VERSION = 1;

    private static final String CREATE_SQL =
            "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            NAME + " TEXT NOT NULL, " +
            USER_DEBT_VAL + " INTEGER NOT NULL, " +
            DEBT_VAL + " INTEGER NOT NULL " + ");";

    private final Set<Listener> mListeners = new HashSet<>();

    private DebtorsRepo(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL);
        createTestData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }

    public void createTestData(SQLiteDatabase db) {
        for (int i = 0; i < 10; i++) {
            addDebtorInner(db,"debtor " + i, 10* i, (10 - i) * 10);
        }
    }

    public List<Debtor> getDebtors() {
        String[] cols = {ID, NAME, USER_DEBT_VAL, DEBT_VAL};
        Cursor c = getReadableDatabase().query(TABLE_NAME, cols, null, null,
                null, null, null);
        List<Debtor> list = new ArrayList<>(c.getColumnCount());
        while (c.moveToNext()) {
            list.add(new Debtor(c.getInt(0),
                    c.getString(1), c.getDouble(2), c.getDouble(3)));
        }
        c.close();
        return  list;
    }

    public Debtor getDebtor(int id) {
        String[] cols = {ID, NAME, USER_DEBT_VAL, DEBT_VAL};
        Cursor c = getReadableDatabase().query(TABLE_NAME, cols, ID + " = " + id,
                null, null, null, null);
        c.moveToFirst();
        Debtor debtor = new Debtor(c.getInt(0),
                c.getString(1), c.getDouble(2), c.getDouble(3));
        c.close();
        return debtor;
    }

    private void addDebtorInner(SQLiteDatabase db, String name, double userDebt, double debt) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(USER_DEBT_VAL, userDebt);
        cv.put(DEBT_VAL, debt);
        db.insert(TABLE_NAME, null, cv);
    }

    public void addDebtor(String name, double userDebt, double debt) {
        addDebtorInner(getWritableDatabase(), name, userDebt, debt);
        notifyChanged();
    }

    private void notifyChanged() {
        for (Listener listener : mListeners) listener.onDataChanged();
    }

    public void setListener(Listener listener) {
        mListeners.add(listener);
    }

    public void removeListener(Listener listener) {
        mListeners.remove(listener);
    }


}
