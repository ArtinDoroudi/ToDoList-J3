package org.example.projectj3.Database;

public class DBConst {

    //Location Table
    public static final String TABLE_LOCATION = "location";
    public static final String LOCATION_COLUMN_ID = "id";
    public static final String LOCATION_COLUMN_NAME = "name";
    public static final String LOCATION_COLUMN_LAT = "latitude";
    public static final String LOCATION_COLUMN_LONG = "longitude";

    public static final String CREATE_TABLE_LOCATION =
            "CREATE TABLE " + TABLE_LOCATION + " (" +
                    LOCATION_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
                    LOCATION_COLUMN_NAME + " VARCHAR(50), " +
                    LOCATION_COLUMN_LAT + " VARCHAR(15), " +
                    LOCATION_COLUMN_LONG + " VARCHAR(15), " +
                    "PRIMARY KEY(" + LOCATION_COLUMN_ID +"));";
}