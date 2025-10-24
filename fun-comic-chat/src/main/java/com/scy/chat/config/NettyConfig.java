package com.scy.chat.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.Objects;

@Component
public class NettyConfig {
    private static final Logger log = LoggerFactory.getLogger(NettyConfig.class);

    /**
     *  netty端口
     */
    @Value("${netty.port}")
    private int port;

    private EventLoopGroup mainGroup= new NioEventLoopGroup();

    private EventLoopGroup subGroup= new NioEventLoopGroup();

    private Channel channel;

    @Autowired
    NettyChannelInitializer nettyChannelInitializer;

    @PostConstruct
    public void start() throws InterruptedException {
        // 创建引导，用于netty启动
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 多线程模式，主线程负责接收客户端请求，子线程负责数据读写
        serverBootstrap.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                // 设置服务端accept队列大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                // TCP Keepalive 机制，实现 TCP 层级的心跳保活功能
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 允许较小的数据包的发送，降低延迟
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(nettyChannelInitializer);
        // 绑定端口，并同步等待成功，即启动Netty Server服务端
        ChannelFuture future = serverBootstrap.bind().sync();
        if (future.isSuccess()) {
            // 启动成功，获取Channel引用
            channel = future.channel();
            log.info("start Netty Server port {}", port);
        }
    }

    /**
     * 关闭 Netty Server
     */
    @PreDestroy
    public void gracefulShutdown() {
        if (Objects.nonNull(channel)) {
            channel.close();
        }
        mainGroup.shutdownGracefully();
        subGroup.shutdownGracefully();
    }
}
