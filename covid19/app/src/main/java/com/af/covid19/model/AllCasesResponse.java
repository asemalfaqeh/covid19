package com.af.covid19.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllCasesResponse {

    @Expose
    @SerializedName("statistic_taken_at")
    private String statistic_taken_at;

    @Expose
    @SerializedName("new_deaths")
    private String new_deaths;

    @Expose
    @SerializedName("new_cases")
    private String new_cases;

    @Expose
    @SerializedName("total_recovered")
    private String total_recovered;

    @Expose
    @SerializedName("total_deaths")
    private String total_deaths;

    @Expose
    @SerializedName("total_cases")
    private String total_cases;

    public String getStatistic_taken_at() {
        return statistic_taken_at;
    }

    public void setStatistic_taken_at(String statistic_taken_at) {
        this.statistic_taken_at = statistic_taken_at;
    }

    public String getNew_deaths() {
        return new_deaths;
    }

    public void setNew_deaths(String new_deaths) {
        this.new_deaths = new_deaths;
    }

    public String getNew_cases() {
        return new_cases;
    }

    public void setNew_cases(String new_cases) {
        this.new_cases = new_cases;
    }

    public String getTotal_recovered() {
        return total_recovered;
    }

    public void setTotal_recovered(String total_recovered) {
        this.total_recovered = total_recovered;
    }

    public String getTotal_deaths() {
        return total_deaths;
    }

    public void setTotal_deaths(String total_deaths) {
        this.total_deaths = total_deaths;
    }

    public String getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(String total_cases) {
        this.total_cases = total_cases;
    }
}
