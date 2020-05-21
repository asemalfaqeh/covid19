package com.af.covid19.datasource.repositories;

import android.app.Application;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import com.af.covid19.datasource.ApiEndPoints;
import com.af.covid19.datasource.RetrofitInstance;
import com.af.covid19.model.AllCasesResponse;
import com.af.covid19.model.DataResponse;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DataRepository extends RetrofitInstance {

    private Application application;
    private MutableLiveData<List<DataResponse.CountriesStat>> dataResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<AllCasesResponse> getallmutable = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable  = new CompositeDisposable();

    public DataRepository(Application application){
        this.application = application;
    }

    public MutableLiveData<List<DataResponse.CountriesStat>> mutableLiveData(){

        ArrayList<DataResponse.CountriesStat> dataResponseArrayList = new ArrayList<>();

        ApiEndPoints apiEndPoints = getRetrofitInstance(application.getApplicationContext());
        Observable<DataResponse> dataResponseObservable = apiEndPoints.getData();
        compositeDisposable.add(dataResponseObservable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<DataResponse>() {
            @Override
            public void onNext(DataResponse countriesStat) {

                for (DataResponse.CountriesStat countriesStat2 : countriesStat.getCountriesStat()){

                    DataResponse.CountriesStat countriesStat1 = new DataResponse.CountriesStat();

                    countriesStat1.setCases(countriesStat2.getCases());
                    countriesStat1.setCountryName(countriesStat2.getCountryName());
                    countriesStat1.setDeaths(countriesStat2.getDeaths());
                    countriesStat1.setNewCases(countriesStat2.getNewCases());
                    countriesStat1.setNewDeaths(countriesStat2.getNewDeaths());
                    countriesStat1.setRegion(countriesStat2.getRegion());
                    countriesStat1.setSeriousCritical(countriesStat2.getSeriousCritical());
                    countriesStat1.setTotalRecovered(countriesStat2.getTotalRecovered());
                    countriesStat1.setActive_cases(countriesStat2.getActive_cases());

                    dataResponseArrayList.add(countriesStat1);

                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(application.getApplicationContext(), e.getMessage()+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                dataResponseMutableLiveData.postValue(dataResponseArrayList);
            }
        }));

        return dataResponseMutableLiveData;

    }

    public MutableLiveData<AllCasesResponse> getAllCasesRepo(){

        AllCasesResponse allCasesResponseob = new AllCasesResponse();
        ApiEndPoints apiEndPoints = getRetrofitInstance(application.getApplicationContext());
        Observable<AllCasesResponse> allCasesResponseObservable = apiEndPoints.getAllCases();
        compositeDisposable.add(allCasesResponseObservable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<AllCasesResponse>() {
            @Override
            public void onNext(AllCasesResponse allCasesResponse) {

               allCasesResponseob.setNew_cases(allCasesResponse.getNew_cases());
               allCasesResponseob.setNew_deaths(allCasesResponse.getNew_deaths());
               allCasesResponseob.setStatistic_taken_at(allCasesResponse.getStatistic_taken_at());
               allCasesResponseob.setTotal_cases(allCasesResponse.getTotal_cases());
               allCasesResponseob.setTotal_deaths(allCasesResponse.getTotal_deaths());
               allCasesResponseob.setTotal_recovered(allCasesResponse.getTotal_recovered());

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(application.getApplicationContext(), e.getMessage() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                getallmutable.postValue(allCasesResponseob);
            }
        }));

        return getallmutable;
    }

    public void clear(){
        compositeDisposable.clear();
    }

}
