package com.migu.schedule.info;

public class Task {
    /**
     * 任务编号
     */
    private int id;
    /**
     * 资源消耗率
     */
    private int consumption;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }
}
