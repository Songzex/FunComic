package com.scy.modles.messcenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TOMessageEntity {
    private String messageId;
    private String message;
    private String messageType;
    private String messageStatus;
    private String messageSendTime;
    private String messageReceiveTime;
    private String messageSendUserId;
    private String messageReceiveUserId;
    private String messageSendUserName;
    private String messageReceiveUserName;
    private String messageSendUserAvatar;
    private String messageReceiveUserAvatar;
    private String messageSendUserEmail;
    private String messageReceiveUserEmail;
    private String messageSendUserPhone;
    private String messageReceiveUserPhone;
}
