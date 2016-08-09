import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.prashanth.nettyxmpp.xml.builder.XmlElement;

import java.net.InetSocketAddress;

/**
 * Created by prashanth on 8/8/16.
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new EchoServerHandler());
                        }
                    });
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() +
                    "ì started and listen on ì" + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
    public static void main(String[] args) throws Exception {
//        if (args.length !=1) {
//            System.err.println(
//                    "Usage: " + EchoServer.class.getSimpleName() +
//                    "<port>");
//        }
//        int port = Integer.parseInt("8888");
//        new EchoServer(port).start();
        XmlElement element = new XmlElement("message");
        element.addAttribute("to", "p.is.prashanth@gmail.com");
        element.setXmlns("jabber:org:message");
        element.setBody("Hello Prashanth");

        XmlElement childOneElement = new XmlElement("child1");
        childOneElement.addAttribute("one", "1");
        childOneElement.addAttribute("two", "2");
        childOneElement.setBody("Hello");
        element.addChild(childOneElement);

        XmlElement childTwoElement = new XmlElement("child2");
        childTwoElement.addAttribute("3", "three");
        childOneElement.addChild(childTwoElement);

        System.out.println(element.toXml());
    }
}

