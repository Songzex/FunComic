package com.scy.modles.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class TaskStatus {
    private String status; // not_started/processing/completed/failed
    private int progress; // 处理进度（0-100）
    private String msg; // 错误信息（失败时）

    public TaskStatus(String status, int progress) {
        this.status = status;
        this.progress = progress;
    }
}