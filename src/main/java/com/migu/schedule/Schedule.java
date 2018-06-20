package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.Task;
import com.migu.schedule.info.TaskInfo;

import java.util.*;

/*
*类名和方法不能修改
 */
public class Schedule {
    /**
     * 任务状态列表
     */
    private List<TaskInfo> mTaskInfoList = new ArrayList<TaskInfo>();
    /**
     * 任务挂起队列
     */
    private Queue<Task> mQueue = new LinkedList<Task>();

    public int init() {
        // TODO 方法未实现
        if(mTaskInfoList != null){
           mTaskInfoList.clear();
        }
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        // TODO 方法未实现
        if(nodeId <= 0){
            return ReturnCodeKeys.E004;
        }
        for(TaskInfo taskInfo:mTaskInfoList){
            if(taskInfo.getNodeId() == nodeId){
                return ReturnCodeKeys.E005;
            }
        }
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setNodeId(nodeId);
        mTaskInfoList.add(taskInfo);
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        // TODO 方法未实现
        /**
         * 如果服务节点编号小于等于0, 返回E004:服务节点编号非法
         */
        if(nodeId <= 0){
            return  ReturnCodeKeys.E004;
        }
        /**
         * 如果服务节点编号未被注册, 返回E007:服务节点不存在
         */
        boolean isRegister = false;
        Iterator<TaskInfo> it = mTaskInfoList.iterator();
        while(it.hasNext()){
            TaskInfo taskInfo = it.next();
             if(taskInfo.getNodeId() == nodeId){
                 /**
                  * 如果该服务节点正运行任务，则将运行的任务移到任务挂起队列中，等待调度程序调度。
                  */
                 if(taskInfo.getTaskId() != 0){
                     Task task = new Task();
                     task.setId(taskInfo.getTaskId());
                     mQueue.add(task);
                 }
                 it.remove();
                 isRegister = true;
             }
        }
        /**
         * 注销成功，返回E006:服务节点注销成功
         */
        if(isRegister){
            return ReturnCodeKeys.E006;
        }else{
            return ReturnCodeKeys.E007;
        }
    }


    public int addTask(int taskId, int consumption) {
        // TODO 方法未实现
        /**
         * 如果任务编号小于等于0, 返回E009:任务编号非法。
         */
        if(taskId <= 0){
            return ReturnCodeKeys.E009;
        }
        /**
         * 如果相同任务编号任务已经被添加, 返回E010:任务已添加
         */
        boolean isAdd = false;
        for(Task task:mQueue){
            if(task.getId() == taskId){
                isAdd = true;
                break;
            }
        }
        if(isAdd){
            return ReturnCodeKeys.E010;
        }else {
            /**
             * 添加成功，返回E008任务添加成功。
             */
            Task task = new Task();
            task.setId(taskId);
            task.setConsumption(consumption);
            mQueue.add(task);
            return ReturnCodeKeys.E008;
        }
    }


    public int deleteTask(int taskId) {
        // TODO 方法未实现
        /**
         * 如果任务编号小于等于0, 返回E009:任务编号非法。
         */
        if(taskId <= 0){
            return ReturnCodeKeys.E009;
        }
        /**
         * 如果指定编号的任务未被添加, 返回E012:任务不存在。
         */
        boolean isAdd = false;
        for(Task task:mQueue){
            if(task.getId() == taskId){
                isAdd = true;
                /**
                 * 删除任务
                 */
                mQueue.remove(task);
                break;
            }
        }
        if(isAdd) {
            /**
             * 删除成功，返回E011:任务删除成功。
             */
            return ReturnCodeKeys.E011;
        }else{
            return ReturnCodeKeys.E012;
        }
    }


    public int scheduleTask(int threshold) {
        // TODO 方法未实现
        
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }

}
