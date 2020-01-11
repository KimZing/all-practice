package com.kimzing.netty.stickpackage.rightbydelimiterbaseedframedecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-09-13 13:20
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 50; i++) {
            ByteBuf byteBuf = Unpooled.copiedBuffer((i + "$").getBytes());
            ctx.writeAndFlush(byteBuf);
        }
    }
}
