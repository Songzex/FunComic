/**
 * -----------------------------------
 * Copyright (c) 2021-2024
 *  All rights reserved, Designed By linfeng.tech , linfengtech.cn
 * 林风婚恋交友商业版本请务必保留此注释头信息
 * 商业版授权联系技术客服	 QQ:  973921677/3582996245
 * 严禁分享、盗用、转卖源码或非法牟利！
 * 版权所有 ，侵权必究！
 * -----------------------------------
 */
package com.scy.utils;

/**
 * 常量
 *
 */
public class Constant {
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;

    public static final String TOKEN_HEADER = "token";


    /** 基础信息配置 **/
    public static final int MAN = 1;
    public static final int WOMAN = 2;

    //redis缓存配置值
    /**微信小程序Id **/
    public static final String WX_MA_APP_ID = "wxMaAppId";
    /**微信小程序密钥 **/
    public static final String WX_MA_APP_SECRET = "wxMaAppSecret";
    /**微信小程序Id **/
    public static final String WX_MP_APP_ID = "wxMpAppId";
    /**微信小程序密钥 **/
    public static final String WX_MP_APP_SECRET = "wxMpAppSecret";
    /**APP微信支付ID**/
    public static final String WX_SUB_APP_ID = "wxSubAppId";
    /**微信支付回调地址 **/
    public static final String WX_PAY_REDIRECT_URL = "wxPayRedirectUrl";
    /**微信商户号 **/
    public static final String WX_MCH_ID = "wxMchId";
    /**微信商户密钥 **/
    public static final String WX_MCH_KEY = "wxMchKey";
    /**退款回调地址**/
    public static final String WX_REFUND_NOTIFY_URL = "wxRefundNotifyUrl";
    /**h5支付之后跳转地址 **/
    public static final String H5_PAY_REDIRECT_URL = "h5PayRedirectUrl";

    public static final String VIP_AGREE_CONTENT = "vipAgreeContent";
    /**普通用户**/
    public static final int COMMON_USER = 0;
    /**会员用户**/
    public static final int VIP_USER = 1;
    /**会员花瓣奖励翻倍数**/
    public static final String VIP_PETAL = "vipPetal";
    /**会员每天免费打招呼次数**/
    public static final String VIP_HI_FREE = "vipHiFree";
    /**会员每天推送嘉宾数量**/
    public static final String VIP_RECOMMEND = "vipRecommend";
    /**普通用户每天免费打招呼次数**/
    public static final String COMMON_HI_FREE = "commonHiFree";
    /**普通用户每天推送嘉宾数量**/
    public static final String COMMON_RECOMMEND = "commonRecommend";

    public static final String GUEST_REFRESH_HOUR = "guestRefreshHour";
    public static final String GUEST_QUERY_HISTORY_DAY = "guestQueryHistoryDay";
    public static final String GUEST_QUERY_LIKE_DAY = "guestQueryLikeDay";
    public static final String GUEST_QUERY_LOOK_DAY = "guestQueryLookDay";

    public static final String GUEST_HELLO_EXPIRE_DAY = "guestHelloExpireDay";

    public static final int GUEST_UN_LOCK = 0;


    /** 花瓣相关配置 **/
    public static final String PETAL_UNLOCK = "petalUnlock";
    public static final String PETAL_HI = "petalHi";
    public static final String PETAL_RECOMMEND = "petalRecommend";
    public static final String PETAL_EXPIRE = "petalExpire";

    public static final String VIP_CLEAR_CRON = "0 0 0 * * ?";
    public static final String PETAL_CLEAR_MONTH_CRON = "0 0 0 1 * ?";
    public static final String PETAL_CLEAR_SEASON_CRON = "0 0 0 1 1,4,7,10 ?";
    public static final String PETAL_CLEAR_YEAR_CRON = "0 0 0 1 1 ? *";

    /** 字典类型 **/
    public static final String DICT_GENDER = "DT_GENDER";
    public static final String DICT_SALARY = "DT_SALARY";
    public static final String DICT_JOB = "DT_JOB";
    public static final String DICT_EDUCATION = "DT_EDUCATION";
    public static final String DICT_MARRIAGE = "DT_MARRIAGE";
    public static final String DT_ACCUSATION_TAG = "DT_ACCUSATION_TAG";

    /** 终端类型 **/
    public static final String TERMINAL_TYPE_APP = "app";
    public static final String TERMINAL_TYPE_H5 = "h5";
    /** 微信小程序 **/
    public static final String TERMINAL_TYPE_WX_MA = "wx_ma";
    /** 微信浏览器（公众号） **/
    public static final String TERMINAL_TYPE_WX_MP = "wx_mp";

    /** 微信支付相关 **/
    public static final String CALLBACK_SUCCESS= "SUCCESS";

    /** 外部数据源 **/
    public static final String TRANSPORT_SOURCE = "transportSource";

    /** 腾讯云接口相关 **/
    public static final String TENCENT_SECRET_ID= "tencentSecretId";
    public static final String TENCENT_SECRET_KEY = "tencentSecretKey";
    public static final String TENCENT_FACE_ID_HOST = "tencentFaceIdHost";
    public static final String TENCENT_DETECT_FACE_HOST = "tencentDetectFaceHost";
    public static final String TENCENT_DETECT_FACE_REGION = "tencentDetectFaceRegion";
    public static final String FACE_QUALITY_SCORE = "faceQualityScore";
    public static final String FACE_COMPARE_SCORE = "faceCompareScore";

    public static final String FACE_REAL_NAME_SCORE = "faceRealNameScore";

    public static final String FACE_DETECT_OPEN = "faceDetectOpen";
    public static final String FACE_COMPARE_OPEN = "faceCompareOpen";
    public static final String FACE_REAL_NAME_OPEN = "faceRealNameOpen";
    public static final String PHONE_REAL_NAME_OPEN = "phoneRealNameOpen";

    public static final String AVATAR_CHECK_OPEN = "avatarCheckOpen";


    public static final String TENCENT_SMS_HOST = "tencentSmsHost";
    public static final String TENCENT_SMS_REGION = "tencentSmsRegion";
    public static final String TENCENT_SMS_SDK_APP_ID = "tencentSmsSdkAppId";
    public static final String TENCENT_SMS_SIGN_NAME = "tencentSmsSignName";
    public static final String TENCENT_SMS_TEMPLATE_ID = "tencentSmsTemplateId";
    public static final String SMS_OPEN = "smsOpen";
    public static final String ALI_SMS_DOMAIN = "aliSmsDomain";
    public static final String ALI_SMS_VERSION = "aliSmsVersion";
    public static final String ALI_SMS_ACTION = "aliSmsAction";
    public static final String ALI_SMS_REGION_ID= "aliSmsRegionId";
    public static final String ALI_SMS_KEY_ID= "aliSmsAccessKeyId";
    public static final String ALI_SMS_SECRET= "aliSmsAccessKeySecret";
    public static final String ALI_SMS_SIGN= "aliSmsSign";
    public static final String ALI_SMS_TEMPLATE_ID= "aliSmsTemplateId";

    /** 百度相关接口 **/
    public static final String BAIDU_APP_ID = "baiduAppId";
    public static final String BAIDU_API_KEY = "baiduApiKey ";
    public static final String BAIDU_SECRET_KEY = "baiduSecretKey";

    public static final String BAIDU_CENSOR_TEXT_OPEN = "baiduCensorTextOpen";
    public static final String BAIDU_CENSOR_IMAGE_OPEN = "baiduCensorImageOpen";
    public static final String BAIDU_CENSOR_VOICE_OPEN = "baiduCensorVoiceOpen";
    public static final String BAIDU_CENSOR_VIDEO_OPEN = "baiduCensorVideoOpen";

    public static final String BAIDU_CENSOR_TEXT_SUSPECTED = "baiduCensorTextSuspected";
    public static final String BAIDU_CENSOR_IMAGE_SUSPECTED = "baiduCensorImageSuspected";
    public static final String BAIDU_CENSOR_VOICE_SUSPECTED = "baiduCensorVoiceSuspected";
    public static final String BAIDU_CENSOR_VIDEO_SUSPECTED = "baiduCensorVideoSuspected";

    /** 腾讯地图相关 **/
    public static final String TENCENT_MAP_GEOCODER_URL = "tencentMapGeocoderUrl";
    public static final String TENCENT_MAP_IP_URL = "tencentMapIpUrl";
    public static final String TENCENT_MAP_KEY = "tencentMapKey";

    /** 七牛云相关 **/
    public static final String QI_NIU_DOMAIN= "qiNiuDomain";
    public static final String QI_NIU_DIRECTORY= "qiNiuDirectory";
    public static final String QI_NIU_ACCESS_KEY= "qiNiuAccessKey";
    public static final String QI_NIU_SECRET_KEY= "qiNiuSecretKey";
    public static final String QI_NIU_BUCKET_NAME= "qiNiuBucketName";

    /** 消息推送相关 **/
    public static final String DIRECT_EXCHANGE_NAME = "lf.direct";
    public static final String QUEUE_PUSH_MESSAGE = "lf.queue.push";

    public static final String PUSH_ROUTING_NAME = "push";

    public static final String HELLO_CONTENT_SEPARATOR = "***";


    /** 漂流瓶 **/
    public static final String PICK_BOTTLE_FREE_TIMES = "pickBottleFreeTimes";
    public static final String THROW_BOTTLE_PRIZE_TIMES = "throwBottlePrizeTimes";

    public static final String THROW_BOTTLE_PRIZE_NUM = "throwBottlePrizeNum";

    /** 重置/提现/账户 **/
    public static final String GIFT_PERCENT = "giftPercent";
    public static final String PETAL_RATE = "petalRate";
    public static final String CASH_COUNT_DAY = "cashCountDay";
    public static final String CASH_COUNT_WEEK = "cashCountWeek";

    /** 搭子 **/
    public static final String MAX_CANCEL_PARTNER_COUNT = "maxCancelPartnerCount";

    /** minio **/
    public static final String OSS_SUPPLIER = "ossSupplier";
    public static final String MINIO_UPLOAD_URL = "minioUploadUrl";
    public static final String MINIO_ACCESS_KEY = "minioAccessKey";
    public static final String MINIO_SECRET_KEY = "minioSecretKey";
    public static final String MINIO_DOMAIN = "minioDomain";
    public static final String MINIO_BUCKET_NAME = "minioBucketName";

    /** 随机文字开放接口 **/
    public static final String RANDOM_TEXT_URL = "randomTextUrl";
    public static final String RANDOM_IMAGE_URL = "randomImageUrl";
    public static final String ROBOT_RANDOM_OPEN = "robotRandomOpen";
}
