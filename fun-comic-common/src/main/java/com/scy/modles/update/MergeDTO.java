package com.scy.modles.update;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public  class MergeDTO {
    private String fileMd5;
    private String fileName;
    private int totalShards;
}