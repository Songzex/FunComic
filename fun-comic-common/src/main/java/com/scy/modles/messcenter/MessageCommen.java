package com.scy.modles.messcenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageCommen {
    private String exchange;
    private String routingKey;
    private TOMessageEntity message;
}
