package com.kimzing.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务端.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-08-31 13:36
 */
public class EchoServer {

    public static void main(String[] args) throws InterruptedException {
        start();
    }

    private static void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        EchoServerHandler serverHandler = new EchoServerHandler();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    // 创建EventLoopGroup
                    .group(group)
                    // 指定通道类型
                    .channel(NioServerSocketChannel.class)
                    // 监听的地址
                    .localAddress(8080)
                    // 子通道的处理
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            // 阻塞，直到连接完成
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("服务器启动完成");
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }

    }
}
