package com.yh.tools;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask Ҳ��һ�ֱ���,future�൱�ڵ����̺߳�ִ���߳��е����������Ƕ���
 * future������
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
        info.name = "��Ʒ";
        return info;
    }
}


class ProductInfo {
    String id;
    String name;
}
