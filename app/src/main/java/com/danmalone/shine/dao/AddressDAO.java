package com.danmalone.shine.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by danmalone on 14/09/2014.
 */

@DatabaseTable(tableName = "address")
public class AddressDAO {

    public static final String ADDRESS_NAME = "name";
    public static final String ADDRESS_CODE= "code";
    public static final String ADDRESS_COUNTRY = "country";

    @DatabaseField(id=true, columnName = ADDRESS_NAME)
    private String country;
    @DatabaseField(columnName = ADDRESS_CODE)
    private String code;
    @DatabaseField(columnName = ADDRESS_COUNTRY)
    private String name;

    public AddressDAO() {
        // ORMLite needs a no-arg constructor
    }
    public AddressDAO(String country, String code, String name) {
        this.name = name;
        this.country = country;
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
