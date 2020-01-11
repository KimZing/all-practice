package com.kimzing.java.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio服务端.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-08-27 11:05
 */
public class NioServer {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) throws IOException {
        new NioServer();
    }

    public NioServer() throws IOException {
        // 初始化操作, 也可以使用SelectorProvider进行初始化
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        selector = Selector.open();
        // 注册接收就绪事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端已经启动");
        // 监听事件
        listen();
    }

    private void listen() throws IOException {
        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 获取事件key后，需要移除该事件key，否则下次仍然会监听
                iterator.remove();
                // 如果该key是有效的，对这个key进行处理
                if (key.isValid()) {
                    handleKey(key);
                }
            }
        }
    }

    private void handleKey(SelectionKey key) throws IOException {
        // 准备接收连接就绪，(PS: 服务端是接收Accept就绪，而对应的客户端就是连接就绪Connect)
        if (key.isAcceptable()) {
            System.out.println("准备接收Channel就绪");
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            // 附带一个介质
            socketChannel.register(selector, SelectionKey.OP_READ, new ArrayList<String>());
        }
        // 通道读就绪
        if (key.isReadable()) {
            System.out.println("Channel读就绪");
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            SocketChannel socketChannel = (SocketChannel) key.channel();
            int count = socketChannel.read(byteBuffer);
            // 如果连接通道已经关闭，则会返回-1,  (向通道写数据时，如果通道已经满了，那么会返回0)
            if (count == -1) {
                System.out.println("通道已经关闭");
                // 可以使用以下两种方式取消对该key的监听
                key.cancel();
                //socketChannel.register(selector, 0);
                return;
            }
            byteBuffer.flip();
            byte[] bytes = new byte[count];
            byteBuffer.get(bytes);
            String recieve = new String(bytes);
            System.out.println("接收到消息" + recieve);
            ArrayList<String> recieves = (ArrayList<String>) key.attachment();
            recieves.add("服务端:" + recieve);
            // 注册写事件
            socketChannel.register(selector, SelectionKey.OP_WRITE, recieves);
        }
        // 通道写就绪
        if (key.isWritable()) {
            System.out.println("Channel写就绪");
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ArrayList<String> recieves = (ArrayList<String>) key.attachment();
            for (String recieve : recieves) {
                byte[] bytes = recieve.getBytes();
                ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
                byteBuffer.put(bytes);
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
            }
            recieves.clear();
            socketChannel.register(selector, SelectionKey.OP_READ, recieves);
        }
    }

}
