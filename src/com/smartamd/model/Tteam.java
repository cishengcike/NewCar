package com.smartamd.model;

import java.util.Date;

public class Tteam {
    private Integer teamid;

    private String creattime;

    private String teamname;

    private String leadername;

    private String leadertel;

    private String leaderaddress;

    private String extra1;

    private String extra2;

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname == null ? null : teamname.trim();
    }

    public String getLeadername() {
        return leadername;
    }

    public void setLeadername(String leadername) {
        this.leadername = leadername == null ? null : leadername.trim();
    }

    public String getLeadertel() {
        return leadertel;
    }

    public void setLeadertel(String leadertel) {
        this.leadertel = leadertel == null ? null : leadertel.trim();
    }

    public String getLeaderaddress() {
        return leaderaddress;
    }

    public void setLeaderaddress(String leaderaddress) {
        this.leaderaddress = leaderaddress == null ? null : leaderaddress.trim();
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