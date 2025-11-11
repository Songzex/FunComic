package com.scy.common.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 24022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Adopter {
    /**
     * 漫画id
     */
    private  Long  rid;

    /**
     * 章节的id序列和名字
     */
    public   String des;
    /**
     * 本节的url
     */
    public String url;
}
