package com.scy.common.modles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCollections {
    /**
     *  comic 漫画
     *  rido 广播剧
     *  noves 小说
     */
    public String  type;
    public Long  rid;
    public String  url;
}
