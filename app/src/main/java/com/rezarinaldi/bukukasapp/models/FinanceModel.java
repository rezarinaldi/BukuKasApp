package com.rezarinaldi.bukukasapp.models;

public class FinanceModel {
    private int id_finance;
    private String date, nominal, description, category;

    public FinanceModel(int id_finance, String date, String nominal, String description, String category) {
        this.id_finance = id_finance;
        this.date = date;
        this.nominal = nominal;
        this.description = description;
        this.category = category;
    }

    public int getId_finance() {
        return id_finance;
    }

    public void setId_finance(int id_finance) {
        this.id_finance = id_finance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
