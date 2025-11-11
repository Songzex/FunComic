package com.scy.common.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author 24022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ridodate")
public class RidoDate {
    /**
     *  文档id
     */
    @Id
    private  String  id;
    private  Long  rid;

    private  List<Adopter> urls;

}
