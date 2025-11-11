package com.scy.common.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "FavoriteFolderState")
public class FavoriteFolderState {
    @Id
    private  String  id;
    public String   userid;
    public String resourceId;
    public String  chapter;
    public String processes;
    public String  type;
}
