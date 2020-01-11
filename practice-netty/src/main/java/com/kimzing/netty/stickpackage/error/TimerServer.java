package com.kimzing.netty.stickpackage.error;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 时间服务器.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-11 16:28
 */
public class TimerServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup parent = new NioEventLoopGroup();
        EventLoopGroup child = new NioEventLoopGroup();
        TimerServerChildHandler childHandler = new TimerServerChildHandler();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parent, child)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(8080)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(childHandler);
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("服务端启动完成");
            future.channel().closeFuture()
                    .addListener((f) -> System.out.println("关闭完成"))
                    .sync();
        } finally {
            parent.shutdownGracefully().sync();
            child.shutdownGracefully().sync();
        }


    }

}
