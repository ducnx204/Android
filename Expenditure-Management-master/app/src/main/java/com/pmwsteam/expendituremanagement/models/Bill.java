package com.pmwsteam.expendituremanagement.models;

public class Bill {
    int idBill;
    String nameBill, dateBill, moneyBill;

    public Bill(int idBill, String nameBill, String dateBill, String moneyBill) {
        this.idBill = idBill;
        this.nameBill = nameBill;
        this.dateBill = dateBill;
        this.moneyBill = moneyBill;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public String getNameBill() {
        return nameBill;
    }

    public void setNameBill(String nameBill) {
        this.nameBill = nameBill;
    }

    public String getDateBill() {
        return dateBill;
    }

    public void setDateBill(String dateBill) {
        this.dateBill = dateBill;
    }

    public String getMoneyBill() {
        return moneyBill;
    }

    public void setMoneyBill(String moneyBill) {
        this.moneyBill = moneyBill;
    }
}
