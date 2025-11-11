package com.scy.common.modles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFeedsFrom implements Serializable {

    /**
     *
     * 标题**/
    private String titlename;

    /**
     * 话题
     */
    private String topic;

    /**
     * 内容
     */
    private String context;

    /**
     * 作者
     */
    private String author;

    /**
     * 创作时间
     */
    private Date createdate;

    /**
     * 帖子的状态
     */
    private String status;

//    /**
//     * 转发数
//     */
//    private String referencecount;
//
//    /**
//     * 点赞数
//     */
//    private String likecount;

    /**
     * 类型（0,1）社区类(社区ID)和 个人空间类（personal）
     */
    private String type;

    /**
     * 用户ID
     */
    private Integer userid;

}
