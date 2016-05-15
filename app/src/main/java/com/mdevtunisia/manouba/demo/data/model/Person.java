package com.mdevtunisia.manouba.demo.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mdevtunisia.manouba.demo.data.schema.TableUtil;

/**
 * Created by Sarra on 10/04/2016.
 */
public class Person implements Parcelable {

    @JsonProperty("id")
    private String token;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String company;
    private String email;
    private String phone;
    private String address;
    private int age;
    private String picture;

    public Person() {
    }

    public Person(String token, String firstName, String lastName, String company, String email, String phone, String address,
                  int age, String picture)
    {
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.picture = picture;
    }

    public Person(Cursor cursor)
    {
        String token = cursor.getString(cursor.getColumnIndex(TableUtil.PersonTable.COLUMN_TOKEN));
        String firstName = cursor.getString(cursor.getColumnIndex(TableUtil.PersonTable.COLUMN_FIRST_NAME));
        String lastName = cursor.getString(cursor.getColumnIndex(TableUtil.PersonTable.COLUMN_LAST_NAME));
        String company = cursor.getString(cursor.getColumnIndex(TableUtil.PersonTable.COLUMN_COMPANY));
        String email = cursor.getString(cursor.getColumnIndex(TableUtil.PersonTable.COLUMN_EMAIL));
        String phone = cursor.getString(cursor.getColumnIndex(TableUtil.PersonTable.COLUMN_PHONE));
        int age = cursor.getInt(cursor.getColumnIndex(TableUtil.PersonTable.COLUMN_AGE));
        String picture = cursor.getString(cursor.getColumnIndex(TableUtil.PersonTable.COLUMN_PICTURE));
        String address = cursor.getString(cursor.getColumnIndex(TableUtil.PersonTable.COLUMN_ADDRESS));

        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.picture = picture;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Person{" +
                "token='" + token + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", picture='" + picture + '\'' +
                '}';
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TableUtil.PersonTable.COLUMN_TOKEN, token);
        contentValues.put(TableUtil.PersonTable.COLUMN_FIRST_NAME, firstName);
        contentValues.put(TableUtil.PersonTable.COLUMN_LAST_NAME, lastName);
        contentValues.put(TableUtil.PersonTable.COLUMN_EMAIL, email);
        contentValues.put(TableUtil.PersonTable.COLUMN_ADDRESS, address);
        contentValues.put(TableUtil.PersonTable.COLUMN_COMPANY, company);
        contentValues.put(TableUtil.PersonTable.COLUMN_PHONE, phone);
        contentValues.put(TableUtil.PersonTable.COLUMN_PICTURE, picture);
        contentValues.put(TableUtil.PersonTable.COLUMN_AGE, age);

        return contentValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.company);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeInt(this.age);
        dest.writeString(this.picture);
    }

    protected Person(Parcel in) {
        this.token = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.company = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.age = in.readInt();
        this.picture = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
