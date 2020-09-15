package com.ydh.redsheep.io.netty.code;

import com.ydh.redsheep.netty.code.protostuff.ProtostuffDecoder;
import com.ydh.redsheep.netty.code.protostuff.ProtostuffEncoder;
import com.ydh.redsheep.netty.model.MessageBO;
import com.ydh.redsheep.netty.util.Constants;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @description:
 * @author: yangdehong
 * @version: 2018/1/9.
 */
public class CodeClient {
    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        try {
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
//                            sc.pipeline().addLast("decoder", new StringDecoder());
//                            sc.pipeline().addLast("encoder", new StringEncoder());
//                            sc.pipeline().addLast("base64Decoder", new Base64Decoder());
//                            sc.pipeline().addLast("base64Encoder", new Base64Encoder());
//                            sc.pipeline().addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(
//                                    this.getClass().getClassLoader()
//                            )));
//                            sc.pipeline().addLast("encoder", new ObjectEncoder());
                            // 自定义编解码器
//                            sc.pipeline().addLast("decoder", new ByteDecoder());
//                            sc.pipeline().addLast("encoder", new ByteEncoder());
//                            sc.pipeline().addLast("decoder", new StringCodec());
//                            sc.pipeline().addLast("encoder", new StringCodec());
                            // jboss-Marshalling
//                            sc.pipeline().addLast("decoder", MarshallingCodeCFactory.buildMarshallingDecoder());
//                            sc.pipeline().addLast("encoder", MarshallingCodeCFactory.buildMarshallingEncoder());
                            // kryo序列号编译器
//                            sc.pipeline().addLast("decoder", new KryoDecoder());
//                            sc.pipeline().addLast("encoder", new KryoEncoder());
                            // protostuff
                            sc.pipeline().addLast("decoder", new ProtostuffDecoder(MessageBO.class));
                            sc.pipeline().addLast("encoder", new ProtostuffEncoder(MessageBO.class));
                            sc.pipeline().addLast(new CodeClientHandler());
                        }
                    });

            ChannelFuture f = b.connect(Constants.HOST, Constants.PORT).sync();

            //压缩
//            f.channel().writeAndFlush("我是从client过来的");
            f.channel().writeAndFlush(new MessageBO(1, "我是从client过来的"));

            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
