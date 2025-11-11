package com.scy.common.modles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RidoPages implements Serializable {
    public Integer PageNum;
    public Integer PageSize;
    public  String ridostaus;
    public  String ridostype;





}
