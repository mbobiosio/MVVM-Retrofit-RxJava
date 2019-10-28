package com.mbobiosio.mvvm_retrofit_rxjava.model.data;

import com.mbobiosio.mvvm_retrofit_rxjava.model.entity.Movie;
import com.mbobiosio.mvvm_retrofit_rxjava.model.entity.Response;
import com.mbobiosio.mvvm_retrofit_rxjava.util.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Mbuodile Obiosio on Oct 28,2019
 * https://twitter.com/cazewonder
 * Nigeria.
 */
public class RetrofitHelper {
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    private MovieService movieService;
    OkHttpClient.Builder builder;

    /**
     * 获取RetrofitHelper对象的单例
     * */
    private static class Singleton {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return Singleton.INSTANCE;
    }

    private RetrofitHelper() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
        movieService = retrofit.create(MovieService.class);
    }

    public void getMovies(Subscriber<Movie> subscriber, int start, int count) {
        movieService.getMovies(start, count)
                .map(new Func1<Response<List<Movie>>, List<Movie>>() {
                    @Override
                    public List<Movie> call(Response<List<Movie>> listResponse) {
                        return listResponse.getSubjects();
                    }
                })
                .flatMap(new Func1<List<Movie>, Observable<Movie>>() {
                    @Override
                    public Observable<Movie> call(List<Movie> movies) {
                        return Observable.from(movies);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}