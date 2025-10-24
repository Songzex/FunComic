package com.scy.chat.core;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

	private static final Logger log = LoggerFactory.getLogger(HeartBeatHandler.class);
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

		//判断evt是否是IdleStateEvent(用于触发用户事件，包含读空闲/写空闲/读写空闲)
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

			if (idleStateEvent.state() == IdleState.READER_IDLE) {

				Channel channel = ctx.channel();
				if(channel == null){
					return;
				}
				AttributeKey<Integer> key = AttributeKey.valueOf("readIdleTimes");
				if(channel.attr(key).get() == null){
					return;
				}
				channel.attr(key).set(channel.attr(key).get() + 1);
				if(channel.attr(key).get() > 3){
					channel.close();
				}
			}
		}
	}
}
