package com.smartamd.model;

/**
 * Created by Aaron on 2015/6/25.
 */
public class QueryLoLa {

    private Double lo;
    private Double la;

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

    @Override
    public String toString() {
        return (getLo()+" "+getLa());
    }
}
