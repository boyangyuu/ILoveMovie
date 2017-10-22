package com.example.yuboyang.ilovemovie1;

import android.app.Application;
import android.os.StrictMode;

import com.example.yuboyang.ilovemovie1.details.DetailsComponent;
import com.example.yuboyang.ilovemovie1.details.DetailsModule;
import com.example.yuboyang.ilovemovie1.listing.ListingComponent;
import com.example.yuboyang.ilovemovie1.listing.ListingModule;
import com.example.yuboyang.ilovemovie1.network.NetworkModule;

/**
 * Created by yuboyang on 10/8/17.
 */

public class BaseApplication extends Application {

    private AppComponent appComponent;
    private ListingComponent listingComponent;
    private DetailsComponent detailsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
        this.appComponent = this.createAppComponent();

//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//                System.out.println("emitter.onNext 1");
//                e.onNext(1);
//                Thread.sleep(2000);
//                System.out.println("e.onNext 2");
//                e.onNext(2);
//                e.onComplete();
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.newThread())
//                .doOnNext(i -> System.out.println("doOnNext : " + i))
//                .map(i -> Arrays.asList(i, 2))
//                .flatMap((s) -> {
//                    System.out.println(Thread.currentThread().getName());
//                    return Observable.fromIterable(s);
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(i -> {
//                            System.out.println("subscribe: " + Thread.currentThread().getName());
//                            System.out.println(i);
//                        },
//                        e -> System.out.println(e.getMessage()),
//                        () -> System.out.println("onC"));
    }

    public AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public ListingComponent createListingComponent() {
        listingComponent = appComponent.plus(new ListingModule());
        return listingComponent;
    }

    public ListingComponent getListComponent() {return this.listingComponent;}

    public DetailsComponent createDetailsComponent() {
        detailsComponent = appComponent.plus(new DetailsModule());
        return detailsComponent;
    }

    public DetailsComponent getDetailComponent() {
        return detailsComponent;
    }
}
