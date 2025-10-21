package com.scy.common.config.netty;

public class Message {
    private String type; // "SINGLE" 或 "GROUP"
    private String fromUserId;
    private String toUserId; // 单聊时使用
    private String groupId;  // 群聊时使用
    private String content;

    // getter 和 setter 方法

}
