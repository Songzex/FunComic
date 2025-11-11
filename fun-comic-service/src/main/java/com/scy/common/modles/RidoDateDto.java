package com.scy.common.modles;

import com.scy.common.mongodb.Adopter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 24022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RidoDateDto {

    private  String  id;
    private  Long  rid;
    private  List<Adopter> urls;

}
