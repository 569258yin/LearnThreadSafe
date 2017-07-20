package com.yh.thread.synchromized;

import java.math.BigInteger;

/**
 * Java �������ʵս
 * ���� synchronized ʵ��ԭ�Ӳ����������зֶ��Ż���
 * ��ʽ�ֽ� + cached
 * Created by kevinyin on 2017/7/15.
 */
public class CachedFactorizer {

    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    //�ܷ�����
    public synchronized long getHits() {
        return hits;
    }

    //�õ�cached��������
    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    public void service(BigInteger i) {
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            //��ִ�й�����ʱ��֮ǰ�ͷ���
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{};
    }
}
