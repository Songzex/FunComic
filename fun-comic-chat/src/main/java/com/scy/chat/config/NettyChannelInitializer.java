package com.scy.chat.config;


import com.scy.chat.core.HeartBeatHandler;
import com.scy.chat.core.WebServiceChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:netty 服务
 * @author JiangCX
 * @time:2023年9月20日 下午9:14:15
 */
@Component
public class NettyChannelInitializer extends ChannelInitializer<SocketChannel>{

	private static final Logger log = LoggerFactory.getLogger(NettyChannelInitializer.class);

	@Autowired
	WebServiceChannelHandler webServiceChannelHandler;
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		log.info(" 管道初始化...... ");
		ChannelPipeline pipeline = ch.pipeline();
		// websocket 基于http协议，所以要有http编解码器
		pipeline.addLast("HttpServerCodec",new HttpServerCodec());
			
		// 对写大数据流的支持 	
		pipeline.addLast(new ChunkedWriteHandler());
		
		// 对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
		// 几乎在netty中的编程，都会使用到此handler
		pipeline.addLast(new HttpObjectAggregator(1024*64));
		
		
		// 增加心跳支持 start
		// 针对客户端，如果在1分钟时没有向服务端发送读写心跳(ALL)，则主动断开
		// 如果是读空闲或者写空闲，不处理
		pipeline.addLast(new IdleStateHandler(30, 0, 0));
		pipeline.addLast(new HeartBeatHandler());
		// 空闲状态检测
		//pipeline.addLast(new ReadTimeoutHandler(5 * 60, TimeUnit.SECONDS));

		// 以下是支持httpWebsocket
		/**
		 * websocket 服务器处理的协议，用于指定给客户端连接访问的路由 : /ws
		 * 本handler会帮你处理一些繁重的复杂的事
		 * 会帮你处理握手动作： handshaking（close, ping, pong） ping + pong = 心跳
		 * 对于websocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同
		 */
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
			
		// 自定义的webSocketHandler
		pipeline.addLast(webServiceChannelHandler);
			
	}

}
