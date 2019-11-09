package com.reyes.lab5reyesmelrose;

public class AndroidCom {
    private int logo;
    String name, coun, ind, ceo, desc;

    public AndroidCom(int logo, String name, String coun, String ind, String ceo, String desc) {
        this.logo = logo;
        this.name = name;
        this.coun = coun;
        this.ind = ind;
        this.ceo = ceo;
        this.desc = desc;
    }

    public int getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public String getCoun() {
        return coun;
    }

    public String getInd() {
        return ind;
    }

    public String getCeo() {
        return ceo;
    }

    public String getDesc() { return desc; }
}