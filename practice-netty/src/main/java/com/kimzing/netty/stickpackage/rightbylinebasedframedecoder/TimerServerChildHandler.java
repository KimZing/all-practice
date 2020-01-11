package com.kimzing.netty.stickpackage.rightbylinebasedframedecoder;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 时间服务器处理类.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-11 16:30
 */
@ChannelHandler.Sharable
public class TimerServerChildHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id() + "接收到新的连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id() + "连接关闭");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String recieve = (String) msg;
        System.out.println(ctx.channel().id() + "接收到消息:" + recieve + ",count:" + count.incrementAndGet());
        //ctx.writeAndFlush(Unpooled.copiedBuffer(recieve, CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(ctx.channel().id() + "发生错误");
    }
}
