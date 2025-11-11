package com.scy.common.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * @author 24022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "MessageHostiry")
public class MongMHostiry {
 @Id
 private  String  id;
    /**
     * 标志
     */
 private   String fromTo;
    /**
     * 聊天集合
     */
 public ArrayList<ChatHoristry> list;


}
