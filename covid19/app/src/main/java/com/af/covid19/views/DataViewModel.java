package com.af.covid19.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.af.covid19.datasource.repositories.DataRepository;
import com.af.covid19.model.AllCasesResponse;
import com.af.covid19.model.DataResponse;

import java.util.List;

public class DataViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    public DataViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
    }

     LiveData<List<DataResponse.CountriesStat>> getDataViewModel(){
        return dataRepository.mutableLiveData();
    }

     LiveData<AllCasesResponse> getAllCasesViewModel(){
        return dataRepository.getAllCasesRepo();
    }

    public void clear(){
        dataRepository.clear();
    }

}
