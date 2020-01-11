package com.kimzing.netty.stickpackage.rightbyfixedlengthframedecoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-13 13:11
 */
public class EchoServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup parent = new NioEventLoopGroup();
        EventLoopGroup child = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parent, child)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(8080)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("$".getBytes());
                            ch.pipeline().addLast(new FixedLengthFrameDecoder(1));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new ServerChildHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("服务端已经启动");
            future.channel().closeFuture().sync();
        } finally {
            parent.shutdownGracefully().sync();
            child.shutdownGracefully().sync();
        }
    }

}
