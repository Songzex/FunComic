package com.scy.chat.core;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.scy.user.service.UserService;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserChannel{

	private final UserService userLoginService;

	private static final Logger log = LoggerFactory.getLogger(WebServiceChannelHandler.class);
	
	private final ConcurrentHashMap<Integer, Channel> userChannelMap = new ConcurrentHashMap<>();

    public UserChannel(UserService userLoginService) {
        this.userLoginService = userLoginService;
    }


    public void addChannel(int uid, Channel channel){
		Channel oldChannel = userChannelMap.get(uid);
		for (Integer uids : userChannelMap.keySet()) {
			log.info("用户ID: " + uids + ", 昵称: " );
		}
		if(oldChannel != null){
			oldChannel.close();
		}

		AttributeKey<String> key = AttributeKey.valueOf("uid");
		channel.attr(key).set(uid+"");
		userChannelMap.put(uid, channel);
		InetSocketAddress socket = (InetSocketAddress) channel.remoteAddress();
		userOnline(uid, socket.getAddress().getHostAddress());
		log.info("用户[" + channel.attr(key).get() + "]已上线");

	}

	public Channel findChannel(int uid){
		return userChannelMap.get(uid);
	}

	public int findUid(Channel channel){
		AttributeKey<String> key = AttributeKey.valueOf("uid");
		return Integer.parseInt(channel.attr(key).get());
	}

	public void removeChannel(int uid){
		userChannelMap.remove(uid);

	}

	public void removeChannel(Channel channel){
		AttributeKey<String> key = AttributeKey.valueOf("uid");
		if(channel.attr(key).get() != null){
			Integer uid = Integer.parseInt(channel.attr(key).get());
			userOffline(uid);
			userChannelMap.remove(uid);
			log.info("用户[" + channel.attr(key).get() + "]已下线");
		}
	}

	private void userOnline(Integer uid, String ip){
//		LambdaQueryWrapper<UserLoginEntity> wrapper = new LambdaQueryWrapper<>();
//		wrapper.eq(UserLoginEntity::getUid, uid);
//		UserLoginEntity userLoginEntity = userLoginService.getOne(wrapper);
//		if(userLoginEntity == null){
//			userLoginEntity = new UserLoginEntity();
//			userLoginEntity.setCreateTime(DateUtil.nowDateTime());
//		}
//		userLoginEntity.setUid(uid);
//		userLoginEntity.setIp(ip);
//		userLoginEntity.setStatus(LoginStatus.ONLINE.getValue());
//		userLoginEntity.setLastLoginTime(DateUtil.nowDateTime());
//		if(userLoginEntity.getId() != null){
//			userLoginEntity.setUpdateTime(DateUtil.nowDateTime());
//			userLoginService.updateById(userLoginEntity);
//		}else{
//			userLoginService.save(userLoginEntity);
//		}
	}

	private void userOffline(Integer uid){
//		LambdaQueryWrapper<UserLoginEntity> wrapper = new LambdaQueryWrapper<>();
//		wrapper.eq(UserLoginEntity::getUid, uid);
//		UserLoginEntity userLoginEntity = userLoginService.getOne(wrapper);
//		userLoginEntity.setStatus(LoginStatus.OFFLINE.getValue());
//		userLoginEntity.setLastOfflineTime(DateUtil.nowDateTime());
//		userLoginEntity.setUpdateTime(DateUtil.nowDateTime());
//		userLoginService.updateById(userLoginEntity);
	}

}
