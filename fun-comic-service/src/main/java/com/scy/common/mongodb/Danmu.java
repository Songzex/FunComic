package com.scy.common.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Danmu")
public class Danmu {
    @Id
    private  String  id;
    public String   content;
    public String  speed;
    public String  start;
    public String    top;
    public String  end;
    public String  rids;
    public String  userid;
    public String  chapter;
}
