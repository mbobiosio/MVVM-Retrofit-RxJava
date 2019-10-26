package com.mbobiosio.mvvm_retrofit_rxjava.model.data;

import com.mbobiosio.mvvm_retrofit_rxjava.model.entity.Movie;
import com.mbobiosio.mvvm_retrofit_rxjava.model.entity.Response;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Mbuodile Obiosio on Oct 26,2019
 * https://twitter.com/cazewonder
 * Nigeria.
 */
public interface MovieService {

    @GET("top250")
    Observable<Response<List<Movie>>> getMovies(@Query("start") int start, @Query("count") int count);
}
