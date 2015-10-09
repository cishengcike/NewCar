package com.smartamd.model;

public class Tremark {
    private Integer remarkid;

    private String remarkuser;

    private String remarkdate;

    private String remarkcontent;

    private Integer result;

    private Integer carid;

    private Integer userid;

    private String extra1;

    private String extra2;

    public Integer getRemarkid() {
        return remarkid;
    }

    public void setRemarkid(Integer remarkid) {
        this.remarkid = remarkid;
    }

    public String getRemarkuser() {
        return remarkuser;
    }

    public void setRemarkuser(String remarkuser) {
        this.remarkuser = remarkuser == null ? null : remarkuser.trim();
    }

    public String getRemarkdate() {
        return remarkdate;
    }

    public void setRemarkdate(String remarkdate) {
        this.remarkdate = remarkdate;
    }

    public String getRemarkcontent() {
        return remarkcontent;
    }

    public void setRemarkcontent(String remarkcontent) {
        this.remarkcontent = remarkcontent == null ? null : remarkcontent.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1 == null ? null : extra1.trim();
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2 == null ? null : extra2.trim();
    }
}