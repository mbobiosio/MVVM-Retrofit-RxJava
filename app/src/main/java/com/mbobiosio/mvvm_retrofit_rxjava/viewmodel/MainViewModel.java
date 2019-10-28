package com.mbobiosio.mvvm_retrofit_rxjava.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;

import com.mbobiosio.mvvm_retrofit_rxjava.model.data.RetrofitHelper;
import com.mbobiosio.mvvm_retrofit_rxjava.model.entity.Movie;
import com.mbobiosio.mvvm_retrofit_rxjava.view.CompletedListener;
import com.mbobiosio.mvvm_retrofit_rxjava.view.MovieAdapter;

import rx.Subscriber;

/**
 * Created by Mbuodile Obiosio on Oct 28,2019
 * https://twitter.com/cazewonder
 * Nigeria.
 */
public class MainViewModel {
    public ObservableField<Integer> contentViewVisibility;
    public ObservableField<Integer> progressBarVisibility;
    public ObservableField<Integer> errorInfoLayoutVisibility;
    public ObservableField<String> exception;
    private Subscriber<Movie> subscriber;
    private MovieAdapter movieAdapter;
    private CompletedListener completedListener;

    public MainViewModel(MovieAdapter movieAdapter,CompletedListener completedListener) {
        this.movieAdapter = movieAdapter;
        this.completedListener = completedListener;
        initData();
        getMovies();
    }

    private void getMovies() {
        subscriber = new Subscriber<Movie>() {
            @Override
            public void onCompleted() {
                Log.d("[MainViewModel]", "onCompleted");
                hideAll();
                contentViewVisibility.set(View.VISIBLE);
                completedListener.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                hideAll();
                errorInfoLayoutVisibility.set(View.VISIBLE);
                exception.set(e.getMessage());
            }

            @Override
            public void onNext(Movie movie) {
                movieAdapter.addItem(movie);
            }
        };
        RetrofitHelper.getInstance().getMovies(subscriber, 0, 20);
    }

    public void refreshData() {
        getMovies();
    }

    private void initData() {
        contentViewVisibility = new ObservableField<>();
        progressBarVisibility = new ObservableField<>();
        errorInfoLayoutVisibility = new ObservableField<>();
        exception = new ObservableField<>();
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.VISIBLE);
    }

    private void hideAll(){
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.GONE);
    }
}