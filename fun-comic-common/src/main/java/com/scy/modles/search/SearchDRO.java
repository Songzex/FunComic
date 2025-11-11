package com.scy.modles.search;

import lombok.Data;

/**
 * @author 24022
 */
@Data
public class SearchDRO {

    private String tags;
    /**
     * 0:免费，1:收费 2:VIP 3:广告解锁
     */
    private String isFree;

    /**
     *
     * 0:连载中，1:完结
     */
    private String status;



    private String lasTest;


}
