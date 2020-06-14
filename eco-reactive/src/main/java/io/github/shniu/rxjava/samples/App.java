package io.github.shniu.rxjava.samples;

import io.reactivex.Observable;

public class App {
    public static void main(String[] args) {
        Observable.just("On", "Off")
                .filter(s -> true)
                .subscribe();
    }
}
