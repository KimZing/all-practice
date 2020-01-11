package com.kimzing.netty.stickpackage.rightbylinebasedframedecoder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 时间客户端处理器.
 * <p>
 *     前面没有别的转换器，所以这里是ByteBuf类型
 * </p>
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-11 16:54
 */
public class TimerClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer("Hello Timer:" + i + System.getProperty("line.separator"), CharsetUtil.UTF_8));
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("some error");
    }
}
