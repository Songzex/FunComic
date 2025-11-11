package com.scy.common.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "LIkeResocrce")
public class LIkeResocrce {
    @Id
    private  String  id;
    /**
     *用户id
     */
    private  Long  rid;
    /**
     * 资源id
     */
    private List<Integer> rodid;
    private List<Integer> comicid;
    private List<Integer> novleid;

}
