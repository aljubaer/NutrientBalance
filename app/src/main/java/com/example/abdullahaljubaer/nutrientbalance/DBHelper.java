package com.example.abdullahaljubaer.nutrientbalance;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "nutrient_balance1.db";
    public static final Integer DATABASE_VERSION = 2;
    public final Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // ---------- VARIETY ---------------

        String queryCreateDB = String.format(
                "CREATE TABLE IF NOT EXISTS `variety` (" +
                        " `variety_name` TEXT NOT NULL, " +
                        " `crop_name` TEXT, " +
                        " `bnf_type` INTEGER, " +
                        " `bnf` REAL, " +
                        " `denitrification_base` REAL, " +
                        " PRIMARY KEY(`variety_name`) " +
                        ");");
        db.execSQL(queryCreateDB);

        String mCSVFile = "variety.csv";
        AssetManager manager = context.getAssets();
        InputStream inStream = null;
        try {
            inStream = manager.open(mCSVFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";
        String columns = "";
        String str1 = "INSERT INTO variety (variety_name,crop_name,bnf_type,bnf,denitrification_base) values(";
        String str2 = ");";

        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                StringBuilder sb = new StringBuilder(str1);
                String[] str = line.split(",");
                sb.append("'" + str[0] + "', ");
                sb.append("'" + str[1] + "', ");
                sb.append("'" + str[2] + "', ");
                sb.append("'" + str[3] + "', ");
                sb.append("'" + str[4] + "'");
                sb.append(str2);
                db.execSQL(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        // ----------------- VARIETY ---------------------


        // ----------------- UPTAKE ----------

        queryCreateDB = String.format("CREATE TABLE IF NOT EXISTS `nutrient_uptake` (\n" +
                "\t`crop_name`\tTEXT NOT NULL,\n" +
                "\t`nutrient_id`\tTEXT NOT NULL,\n" +
                "\t`value`\tREAL,\n" +
                "\tPRIMARY KEY(`nutrient_id`,`crop_name`)\n" +
                ");");
        db.execSQL(queryCreateDB);

        mCSVFile = "nutrient_uptake.csv";
        manager = context.getAssets();
        inStream = null;
        try {
            inStream = manager.open(mCSVFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buffer = new BufferedReader(new InputStreamReader(inStream));
        line = "";
        columns = "";
        str1 = "INSERT INTO `nutrient_uptake` (crop_name,nutrient_id,value) VALUES (";
        str2 = ");";

        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                StringBuilder sb = new StringBuilder(str1);
                String[] str = line.split(",");
                sb.append("'" + str[0] + "', ");
                sb.append("'" + str[1] + "', ");
                sb.append("'" + str[2] + "'");
                sb.append(str2);
                db.execSQL(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        // -----------------UPTAKE -----------------------


        // ----------------- CONCENTRATION ------------

        queryCreateDB = String.format("CREATE TABLE IF NOT EXISTS `nutrient_concentration` (\n" +
                "\t`variety_name`\tTEXT NOT NULL,\n" +
                "\t`nutrient_id`\tTEXT NOT NULL,\n" +
                "\t`part1`\tREAL,\n" +
                "\t`part2`\tREAL,\n" +
                "\tPRIMARY KEY(`variety_name`,`nutrient_id`)\n" +
                ");");
        db.execSQL(queryCreateDB);

        mCSVFile = "nutrient_concentration.csv";
        manager = context.getAssets();
        inStream = null;
        try {
            inStream = manager.open(mCSVFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buffer = new BufferedReader(new InputStreamReader(inStream));
        line = "";
        columns = "";
        str1 = "INSERT INTO `nutrient_concentration` (variety_name,nutrient_id,part1,part2) VALUES (";
        str2 = ");";

        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                StringBuilder sb = new StringBuilder(str1);
                String[] str = line.split(",");
                sb.append("'" + str[0] + "', ");
                sb.append("'" + str[1] + "', ");
                sb.append("'" + str[2] + "', ");
                sb.append("'" + str[3] + "'");
                sb.append(str2);
                db.execSQL(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        // ---------------------- CONCENTRATION ------------------------


        // --------------------- FERTILIZER ----------------------

        queryCreateDB = String.format("CREATE TABLE IF NOT EXISTS `fertilizer` (\n" +
                "\t`fertilizer_name`\tTEXT NOT NULL,\n" +
                "\t`nutrient_id`\tTEXT NOT NULL,\n" +
                "\t`value`\tREAL,\n" +
                "\tPRIMARY KEY(`fertilizer_name`,`nutrient_id`)\n" +
                ");\n");
        db.execSQL(queryCreateDB);

        mCSVFile = "fertilizer.csv";
        manager = context.getAssets();
        inStream = null;
        try {
            inStream = manager.open(mCSVFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buffer = new BufferedReader(new InputStreamReader(inStream));
        line = "";
        columns = "";
        str1 = "INSERT INTO `fertilizer` (fertilizer_name,nutrient_id,value) VALUES (";
        str2 = ");";

        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                StringBuilder sb = new StringBuilder(str1);
                String[] str = line.split(",");
                sb.append("'" + str[0] + "', ");
                sb.append("'" + str[1] + "', ");
                sb.append("'" + str[2] + "'");
                sb.append(str2);
                db.execSQL(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        // --------------------- FERTILIZER ----------------------


        // ----------------- EROSION ------------

        queryCreateDB = String.format("CREATE TABLE IF NOT EXISTS `erosion` (\n" +
                "\t`aez_id`\tINTEGER NOT NULL,\n" +
                "\t`nutrient_id`\tTEXT NOT NULL,\n" +
                "\t`value`\tREAL,\n" +
                "\tPRIMARY KEY(`aez_id`,`nutrient_id`)\n" +
                ");");
        db.execSQL(queryCreateDB);

        mCSVFile = "erosion.csv";
        manager = context.getAssets();
        inStream = null;
        try {
            inStream = manager.open(mCSVFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buffer = new BufferedReader(new InputStreamReader(inStream));
        line = "";
        columns = "";
        str1 = "INSERT INTO `erosion` (aez_id,nutrient_id,value) VALUES (";
        str2 = ");";

        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                StringBuilder sb = new StringBuilder(str1);
                String[] str = line.split(",");
                sb.append("'" + str[0] + "', ");
                sb.append("'" + str[1] + "', ");
                sb.append("'" + str[2] + "'");
                sb.append(str2);
                db.execSQL(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        // ---------------------- EROSION ------------------------


        // --------------------- CROP ----------------------

        queryCreateDB = String.format("CREATE TABLE IF NOT EXISTS `crop` (\n" +
                "\t`crop_name`\tTEXT NOT NULL,\n" +
                "\t`p_r_ratio`\tREAL,\n" +
                "\t`yield`\tREAL,\n" +
                "\tPRIMARY KEY(`crop_name`)\n" +
                ");");
        db.execSQL(queryCreateDB);

        mCSVFile = "crop.csv";
        manager = context.getAssets();
        inStream = null;
        try {
            inStream = manager.open(mCSVFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buffer = new BufferedReader(new InputStreamReader(inStream));
        line = "";
        columns = "";
        str1 = "INSERT INTO `crop` (crop_name,p_r_ratio,yield) VALUES (";
        str2 = ");";

        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                StringBuilder sb = new StringBuilder(str1);
                String[] str = line.split(",");
                sb.append("'" + str[0] + "', ");
                sb.append("'" + str[1] + "', ");
                sb.append("'" + str[2] + "'");
                sb.append(str2);
                db.execSQL(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        // --------------------- CROP ----------------------

        // ----------------- AEZ ------------

        queryCreateDB = String.format("CREATE TABLE IF NOT EXISTS `aez` (\n" +
                "\t`aez_id`\tINTEGER NOT NULL,\n" +
                "\t`avg_rainfall`\tREAL,\n" +
                "\t`fertility_class`\tTEXT,\n" +
                "\tPRIMARY KEY(`aez_id`)\n" +
                ");");
        db.execSQL(queryCreateDB);

        mCSVFile = "aez.csv";
        manager = context.getAssets();
        inStream = null;
        try {
            inStream = manager.open(mCSVFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buffer = new BufferedReader(new InputStreamReader(inStream));
        line = "";
        columns = "";
        str1 = "INSERT INTO `aez` (aez_id,avg_rainfall,fertility_class) VALUES (";
        str2 = ");";

        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                StringBuilder sb = new StringBuilder(str1);
                String[] str = line.split(",");
                sb.append("'" + str[0] + "', ");
                sb.append("'" + str[1] + "', ");
                sb.append("'" + str[2] + "'");
                sb.append(str2);
                db.execSQL(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        // ---------------------- EROSION ------------------------


        // --------------------- CROP ----------------------

        queryCreateDB = String.format("CREATE TABLE IF NOT EXISTS `sedimentation` (\n" +
                "\t`land_type`\tTEXT NOT NULL,\n" +
                "\t`nutrient_id`\tTEXT NOT NULL,\n" +
                "\t`value`\tREAL,\n" +
                "\tPRIMARY KEY(`nutrient_id`,`land_type`)\n" +
                ");");
        db.execSQL(queryCreateDB);

        mCSVFile = "sedimentation.csv";
        manager = context.getAssets();
        inStream = null;
        try {
            inStream = manager.open(mCSVFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buffer = new BufferedReader(new InputStreamReader(inStream));
        line = "";
        columns = "";
        str1 = "INSERT INTO `sedimentation` (land_type,nutrient_id,value) VALUES (";
        str2 = ");";

        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                StringBuilder sb = new StringBuilder(str1);
                String[] str = line.split(",");
                sb.append("'" + str[0] + "', ");
                sb.append("'" + str[1] + "', ");
                sb.append("'" + str[2] + "'");
                sb.append(str2);
                db.execSQL(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        // --------------------- CROP ----------------------



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS variety");
        db.execSQL("DROP TABLE IF EXISTS `nutrient_uptake`");
        db.execSQL("DROP TABLE IF EXISTS `nutrient_concentration`");
        db.execSQL("DROP TABLE IF EXISTS `fertilizer`");
        db.execSQL("DROP TABLE IF EXISTS sedimentation");
        db.execSQL("DROP TABLE IF EXISTS aez");
        db.execSQL("DROP TABLE IF EXISTS crop");
        db.execSQL("DROP TABLE IF EXISTS erosion");

        onCreate(db);

    }


    public int[] numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int[] numRows = new int[8];
        numRows[0] = (int) DatabaseUtils.queryNumEntries(db, "variety");
        numRows[1] = (int) DatabaseUtils.queryNumEntries(db, "nutrient_uptake");
        numRows[2] = (int) DatabaseUtils.queryNumEntries(db, "nutrient_concentration");
        numRows[3] = (int) DatabaseUtils.queryNumEntries(db, "fertilizer");
        numRows[4] = (int) DatabaseUtils.queryNumEntries(db, "aez");
        numRows[5] = (int) DatabaseUtils.queryNumEntries(db, "crop");
        numRows[6] = (int) DatabaseUtils.queryNumEntries(db, "erosion");
        numRows[7] = (int) DatabaseUtils.queryNumEntries(db, "sedimentation");

        return numRows;
    }




}
