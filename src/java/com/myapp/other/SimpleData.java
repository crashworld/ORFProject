package com.myapp.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Posudevskiy
 */
public class SimpleData {

    private String id;
    private static int firstEl = 1;
    private int secondEl = 0;
    private String idFirst;
    private String idSecond;
    private String state;
    private String beginTime;
    private String endTime;
    private String duration;
    private String speed;
    private String track;
    private String dayTime;
    private boolean loaded;
    private SimpleData parent;
    private List<SimpleData> records = new ArrayList<SimpleData>();

    public SimpleData(String dayTime) {
        this.dayTime = dayTime;
        this.idFirst = Integer.toString(firstEl++);
    }

    public SimpleData(SimpleData parent, String state, String timeFirst, String timeSecond, String duration, String speed, String track) {
//        this(state);
        this.parent = parent;
        this.state = state;
        this.beginTime = timeFirst;
        this.endTime = timeSecond;
        this.duration = duration;
        this.speed = speed;
        this.track = track;
        this.idSecond = Integer.toString(parent.getIncrEl());
        this.parent.records.add(this);
    }

    public SimpleData getParent() {
        return parent;
    }

    public String getId() {
        if (getParent() == null) {
            return idFirst;
        } else {
            return getParent().getId() + "." + idSecond;
        }

    }

    public int getIncrEl() {
        return secondEl++;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getDuration() {
        return duration;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getSpeed() {
        return speed;
    }

    public String getState() {
        return state;
    }

    public String getTrack() {
        return track;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public List<SimpleData> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public String toString() {
        if (dayTime != null) {
            return dayTime;
        } else {
            return state;
        }

    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
