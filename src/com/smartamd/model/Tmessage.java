package com.smartamd.model;


public class Tmessage {
    private Integer messageid;

    private String messageuser;

    private String messagetime;

    private String messageaddress;

    private String messagecontent;

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public String getMessageuser() {
        return messageuser;
    }

    public void setMessageuser(String messageuser) {
        this.messageuser = messageuser == null ? null : messageuser.trim();
    }

    public String getMessagetime() {
        return messagetime;
    }

    public void setMessagetime(String messagetime) {
        this.messagetime = messagetime;
    }

    public String getMessageaddress() {
        return messageaddress;
    }

    public void setMessageaddress(String messageaddress) {
        this.messageaddress = messageaddress == null ? null : messageaddress.trim();
    }

    public String getMessagecontent() {
        return messagecontent;
    }

    public void setMessagecontent(String messagecontent) {
        this.messagecontent = messagecontent == null ? null : messagecontent.trim();
    }
}