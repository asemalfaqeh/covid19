package com.af.covid19.datasource;

import com.af.covid19.model.AllCasesResponse;
import com.af.covid19.model.DataResponse;
import com.af.covid19.utils.SecurityConstants;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiEndPoints {


    @GET("cases_by_country.php")
    Observable<DataResponse> getData();

    @GET("worldstat.php")
    Observable<AllCasesResponse> getAllCases();

}
