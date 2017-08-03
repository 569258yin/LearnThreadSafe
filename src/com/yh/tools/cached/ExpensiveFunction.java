package com.yh.tools.cached;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by kevinyin on 2017/8/3.
 */
public class ExpensiveFunction implements Computable<String,BigInteger>{
    Random random = new Random();
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        // 在经过长时间的计算
        Thread.sleep(random.nextInt(5) * 1000);
        return new BigInteger(arg);
    }
}
