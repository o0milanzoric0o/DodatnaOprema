package rs.dodatnaoprema.dodatnaoprema.models;

/**
 * Created by milan on 4/20/2016.
 */

import android.net.Uri;

import java.io.Serializable;

public class User implements Serializable {
    Uri photo;
    String id;
    String name;
    String email;
    String general_name;
    String last_name;
    String address;
    String zip_code;
    String city;
    String phone;
    String mobile;
    String userName;
    String userType;
    String userFirmName;
    String firmID;
    String firmPIB;
    String firmAddress;

    public User() {
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo = null;
        this.general_name=null;
        this.last_name=null;
        this.address=null;
        this.zip_code=null;
        this.city=null;
        this.phone=null;
        this.mobile=null;
        this.userName=null;
        this.userType=null;
        this.userFirmName=null;
        this.firmID=null;
        this.firmPIB=null;
        this.firmAddress=null;
    }

    public User(String id, String name, String email, Uri photo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo = null;
        if (photo != null)
            this.photo = photo;
        this.general_name=null;
        this.last_name=null;
        this.address=null;
        this.zip_code=null;
        this.city=null;
        this.phone=null;
        this.mobile=null;
        this.userName=null;
        this.userType=null;
        this.userFirmName=null;
        this.firmID=null;
        this.firmPIB=null;
        this.firmAddress=null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public String getGeneral_name() {
        return general_name;
    }

    public void setGeneral_name(String general_name) {
        this.general_name = general_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserFirmName() {
        return userFirmName;
    }

    public void setUserFirmName(String userFirmName) {
        this.userFirmName = userFirmName;
    }

    public String getFirmID() {
        return firmID;
    }

    public void setFirmID(String firmID) {
        this.firmID = firmID;
    }

    public String getFirmPIB() {
        return firmPIB;
    }

    public void setFirmPIB(String firmPIB) {
        this.firmPIB = firmPIB;
    }

    public String getFirmAddress() {
        return firmAddress;
    }

    public void setFirmAddress(String firmAddress) {
        this.firmAddress = firmAddress;
    }
}