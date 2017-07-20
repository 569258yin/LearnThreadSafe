package com.yh.thread.synchromized;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ʵ����װ��ͨ�����̲߳���ȫ�����ݽ��з�װ�ڶ����ڲ���������ȷ�ļ������ƣ�ʵ��ͬ��
 *
 * ͨ����ջ��ƣ� ���ɱ����ݶ�������������С�������ķ�Χ��������ɼ��ķ����м���
 *
 * �ο�  Collections.synchronizedList(List)  ����װ����ģʽ����������ʵ�ַ��
 * * Created by kevinyin on 2017/7/20.
 */
public class PersonSet {
    private final Set<String> mySet = new HashSet<>();

    public synchronized void addPerson(String s){
        mySet.add(s);
    }

    public synchronized boolean containsStr(String s){
        return mySet.contains(s);
    }
}
