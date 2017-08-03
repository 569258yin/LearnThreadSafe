package com.yh.tools;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask 也是一种闭锁,future相当于调用线程和执行线程中的桥梁，他们都有
 * future的引用
 * <p>
 * Created by kevinyin on 2017/8/3.
 */
public class Preloader {
    private final FutureTask<ProductInfo> future =
            new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
                @Override
                public ProductInfo call() throws Exception {
                    return loadProductInfo();
                }
            });

    private final Thread thread = new Thread(future);

    public void start(){
        thread.start();
    }

    public ProductInfo get() throws ExecutionException, InterruptedException {
            return future.get();
    };

    private ProductInfo loadProductInfo() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ProductInfo info = new ProductInfo();
        info.id = "10";
        info.name = "产品";
        return info;
    }
}


class ProductInfo {
    String id;
    String name;
}
