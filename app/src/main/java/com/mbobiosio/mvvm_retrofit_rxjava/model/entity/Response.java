package com.mbobiosio.mvvm_retrofit_rxjava.model.entity;

/**
 * Created by Mbuodile Obiosio on Oct 26,2019
 * https://twitter.com/cazewonder
 * Nigeria.
 */
public class Response<T> {
    private T subjects;
    private int count;
    private int start;
    private int total;

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }

    public T getSubjects() {

        return subjects;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}