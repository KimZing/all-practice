package com.kimzing.netty.timer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间服务器处理类.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-11 16:30
 */
@ChannelHandler.Sharable
public class TimerServerChildHandler extends ChannelInboundHandlerAdapter {

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
        ByteBuf byteBuf = (ByteBuf) msg;
        String recieve = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println(ctx.channel().id() + "接收到消息" + recieve);
        ctx.write(byteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id() + "读取完成");

        LocalDateTime now = LocalDateTime.now();
        String formatTime = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        // 写入时间并且关闭该通道
        ctx.writeAndFlush(Unpooled.copiedBuffer(formatTime, Charset.forName("UTF-8"))).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(ctx.channel().id() + "发生错误");
    }
}
