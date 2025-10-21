package com.scy.common.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "favofolder")
public class FavoriteFolder {
    @Id
    public  String id;
    public String  userid;
    public HashSet<String> rlist;
    public HashSet<String> nlist;
    public HashSet<String> clist;
}
