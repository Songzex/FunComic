package com.scy.chat.core;

import com.google.gson.Gson;
import com.scy.chat.message.ConnectMessage;
import com.scy.chat.message.WebsocketMessage;
import com.scy.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:处理消息的handler
 * 				TextWebSocketFrame： 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 * SimpleChannelInboundHandler：	对于请求来讲 ，相当于 【入站，入境】
 * @author hemiao
 * @time:2020年5月20日 上午9:07:16
 */
@Component
@ChannelHandler.Sharable
public class WebServiceChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	
	private static final Logger log = LoggerFactory.getLogger(WebServiceChannelHandler.class);

	private final static Gson gson = new Gson();
	final JwtUtil jwtUtil;

	final
	UserChannel userChannel;

	public WebServiceChannelHandler(JwtUtil jwtUtil, UserChannel userChannel) {
		this.jwtUtil = jwtUtil;
		this.userChannel = userChannel;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {

		// 获取客户端传输过来的消息,只有上报连接消息走webSocket,绑定channel和uid
		// 其他消息走http，webSocket只做推送
		Channel channel = ctx.channel();
		String content = msg.text();

		WebsocketMessage websocketMessage = gson.fromJson(content, WebsocketMessage.class);
		if (websocketMessage.getType().equals("heart")){
			AttributeKey<Integer> key = AttributeKey.valueOf("readIdleTimes");
			channel.attr(key).set(0);
			return;
		}
		ConnectMessage connectRequest = gson.fromJson(content, ConnectMessage.class);
		String token = connectRequest.getToken();
		Claims claims = jwtUtil.getClaimByToken(token);
		AttributeKey<String> key = AttributeKey.valueOf("uid");
		channel.attr(key).set(claims.getSubject());
		userChannel.addChannel(Integer.parseInt(channel.attr(key).get()), channel);

	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) {
		Channel channel = ctx.channel();
		userChannel.removeChannel(channel);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		Channel channel = ctx.channel();
		userChannel.removeChannel(channel);
		log.error("webSocket连接异常", cause);
	}

}
