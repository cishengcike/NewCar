package com.smartamd.model;

public class ServiceStation {
    private Integer id;

    private Integer type;

    private String linkman;

    private String stationname;

    private String stationaddress;

    private String stationtel;

    private Double lo;

    private Double la;

    private String extra1;

    private String extra2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname == null ? null : stationname.trim();
    }

    public String getStationaddress() {
        return stationaddress;
    }

    public void setStationaddress(String stationaddress) {
        this.stationaddress = stationaddress == null ? null : stationaddress.trim();
    }

    public String getStationtel() {
        return stationtel;
    }

    public void setStationtel(String stationtel) {
        this.stationtel = stationtel == null ? null : stationtel.trim();
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