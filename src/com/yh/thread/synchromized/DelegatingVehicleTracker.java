package com.yh.thread.synchromized;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将线程安全委托给ConcurrentHashMap
 * Created by kevinyin on 2017/7/20.
 */
public class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String,Point> locations;
    private final Map<String,Point> unmodifiableMap;

    public DelegatingVehicleTracker(ConcurrentHashMap<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return locations;
    }

    public Point getLocation(String id){
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y){
        if (locations.replace(id,new Point(x, y)) == null){
            throw new IllegalArgumentException("invalid vehicle name: "+ id);
        }
    }

}
