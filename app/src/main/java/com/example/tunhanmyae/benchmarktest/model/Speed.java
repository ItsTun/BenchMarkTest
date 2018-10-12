package com.example.tunhanmyae.benchmarktest.model;

public class Speed {
    private double upload;
    private double download;
    private double ping;
    private String date;

    public Speed() {
    }

    public Speed(double upload, double download, double ping, String date) {
        this.upload = upload;
        this.download = download;
        this.ping = ping;
        this.date = date;
    }

    public double getUpload() {
        return upload;
    }

    public void setUpload(double upload) {
        this.upload = upload;
    }

    public double getDownload() {
        return download;
    }

    public void setDownload(double download) {
        this.download = download;
    }

    public double getPing() {
        return ping;
    }

    public void setPing(double ping) {
        this.ping = ping;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
