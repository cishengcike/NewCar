package com.smartamd.model;

public class Tuser {
    private Integer userid;

    private Integer teamid;

    private Integer carid;

    private String usertype;

    private String username;

    private String password;

    private String email;

    private String tel;

    private String address;

    private String status;

    private Double lo;

    private Double la;

    private String CID;

    private String logintime;

    private Integer longcount;

    private Double area;

    private String crops;

    private byte[] photo;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Double getLo() {
        return lo;
    }

    public void setLo(Double lo) {
        this.lo = lo;
    }

    public Double getLa() {
        return la;
    }

    public void setLa(Double la) {
        this.la = la;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public Integer getLongcount() {
        return longcount;
    }

    public void setLongcount(Integer longcount) {
        this.longcount = longcount;
    }

    public Double getArea() {return area;}

    public void setArea(Double area) {this.area = area;}

    public String getCrops() {return crops;}

    public void setCrops(String crops) {this.crops = crops == null ? null : crops.trim();}

    public byte[] getPhoto() {return photo;}

    public void setPhoto(byte[] photo) {this.photo = photo;}

    @Override
    public String toString() {
        return ("userid=" + userid + "  username" + username + "  tel" + tel + "\n");
    }
}