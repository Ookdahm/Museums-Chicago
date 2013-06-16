package com.androidsfuture.museumschicago;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns {
	
    public static final String KEY_ROWID_01 = "_id";
    public static final String KEY_MUSEUM = "museum";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_HOURS = "hours";
    public static final String KEY_ADMISSION = "admission";
    public static final String KEY_CONTACT = "contact";
    public static final String KEY_COLLECTIONS = "collections";
    public static final String KEY_EVENTS = "events";
    public static final String KEY_TYPE = "type";
    public static final String KEY_MEMBERSHIP = "membership";
    public static final String KEY_DONATE = "donate";
    public static final String KEY_PRESS = "press";
    public static final String KEY_INTERN = "intern";
    public static final String KEY_RENTAL = "rental";
    public static final String KEY_PARKING = "parking";
    public static final String KEY_VOLUNTEER = "volunteer";
    public static final String KEY_WEBSITE = "website";
    
    
    public final String TABLE_NAME = "museums";

}
