package com.scy.common.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "ChatHoristry")
public class ChatHoristry {
    private  String   fuserid;
    private  String   nikename;
    private  String   tousrid;
    private  Boolean  isme;
    private  String   userpic;
    private  String   type;
    private  String   data;
    private  String   time;
    private  String   gstime;
}
