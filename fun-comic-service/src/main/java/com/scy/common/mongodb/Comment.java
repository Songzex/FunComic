package com.scy.common.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {
    @Id
    private  String  id;  //文档id
    private  String  type; //id Articleid name date commentcontext  SecondComment

    private  String  typeId;//可以是小说 漫画 帖子

    private  String name;

    private  String date;

    private String text;
    private String photo;
    private String toUser;

    private List<SecondComment> SecondComment;



}
