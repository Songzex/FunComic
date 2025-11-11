package com.scy.common.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class SecondComment {

    private  String firstId;

    private  String name;

    private  String date;

    private String text;

    private String photo;
    private String toUser;


}
