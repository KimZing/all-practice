package com.kimzing.java.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio客户端，与服务端代码类似，不做详细阐述了.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-08-27 11:39
 */
public class NioClient {

    private Selector selector;

    private SocketChannel socketChannel;

    public static void main(String[] args) throws IOException, InterruptedException {
        new NioClient();
    }

    public NioClient() throws IOException, InterruptedException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        listen();
    }

    private void listen() throws IOException {
        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isValid()) {
                    handleKey(key);
                }
            }
        }
    }

    private int num;

    private void handleKey(SelectionKey key) throws IOException {
        // 连接就绪 (PS: 服务端是接收Accept就绪，而对应的客户端就是连接就绪Connect)
        if (key.isConnectable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();

            // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调用channel.finishConnect();才能完成连接
            if (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();
            }
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        }

        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = socketChannel.read(byteBuffer);
            if (read > 0) {
                byteBuffer.flip();
                byte[] bytes = new byte[read];
                byteBuffer.get(bytes);
                System.out.println("客户端收到消息:" + new String(bytes));
            }
            // 写入10个信息就停止
            if (num < 10) {
                socketChannel.register(selector, SelectionKey.OP_WRITE);
            }
        }

        if (key.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            String sendMsg = "hello " + num++;
            byteBuffer.put(sendMsg.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
    }

}
