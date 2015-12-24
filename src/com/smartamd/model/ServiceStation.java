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

    private String products;

    private String sales;

    private Byte star;

    private byte[] photo;

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
        this.linkman = linkman == null ? null : linkman.trim();
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

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products == null ? null : products.trim();
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales == null ? null : sales.trim();
    }

    public Byte getStar() {
        return star;
    }

    public void setStar(Byte star) {
        this.star = star;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}