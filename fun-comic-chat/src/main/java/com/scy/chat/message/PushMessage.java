package com.scy.chat.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PushMessage {
    /**
     * 发送者uid
     */
    private Integer senderUid;
    /**
     * 接收者uid
     */
    private Integer receiverUid;
    /**
     * 消息类型
     */
    private String type;
    /**
     * 消息数据
     */
    private Object data;
}
