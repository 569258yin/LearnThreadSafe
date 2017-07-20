package com.yh.thread.synchromized;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Java监视器模式 将对象域封闭起来，并有内置锁进行保护
 *
 * 以这种每次都复制出一个对象来维持线程安全，当访问量较多时，性能消耗极大；
 * Created by kevinyin on 2017/7/20.
 */
public class MonitorVehicleTracker {
    private final Map<String,MutanlePoint>  locations;

    public MonitorVehicleTracker(Map<String, MutanlePoint> locations) {
        this.locations = deepCopy(locations);
    }
    public synchronized MutanlePoint getLocation(String id){
        MutanlePoint loc = locations.get(id);
        return loc == null ? null : new MutanlePoint(loc);
    }

    public synchronized void setLocation(String id,int x, int y){
        MutanlePoint loc = locations.get(id);
        if(loc == null)
            throw new IllegalArgumentException("No such ID: " + id);
        loc.x = x;
        loc.y = y;
    }


    private static Map<String,MutanlePoint> deepCopy(Map<String,MutanlePoint> m){
        Map<String,MutanlePoint> result = new HashMap<>(m.size());
        for (String id :
                m.keySet()) {
            result.put(id,new MutanlePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
