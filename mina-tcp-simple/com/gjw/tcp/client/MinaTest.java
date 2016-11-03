package com.gjw.tcp.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaTest {
	/**
	 * 在介绍架构之前先认识几个接口�?
	 * IoAccepter 相当于网络应用程序中的服务器�?
	 * IoConnector 相当于客户端
	 * IoSession 当前客户端到服务器端的一个连接实�?
	 * IoHandler 业务处理逻辑
	 * IoFilter 过滤器用于悬接�?讯层接口与业务层接口
	 * */
	/**
	 * To construct a Client, we need to do following
	 * 	1.Create a Connector
	 * 	2.Create a Filter Chain
	 * 	3.Create a IOHandler and add to Connector
	 * 	4.Bind to Server
	 */
	
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 9123;
	private static final long CONNECT_TIMEOUT = 30*1000L; // 30 seconds
	 
	// Set this to false to use object serialization instead of custom codec.
	private static final boolean USE_CUSTOM_CODEC = true;
		
	public static void main(String[] args) throws Throwable {
		String atrss[] = {"我是中国人A","我是中国人B","我是中国人C"};
		if (atrss.length == 0) {
			System.out.println("Please specify the list of any integers");
			return;
		}
		
		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
		    
		if (USE_CUSTOM_CODEC) {
			connector.getFilterChain().addLast("codec",
		    new ProtocolCodecFilter(new TextLineCodecFactory( Charset.forName("GBK"))));//编码解码
		} else {
		    connector.getFilterChain().addLast("codec",
		        new ProtocolCodecFilter(
		        		new ObjectSerializationCodecFactory()));
		}

	    connector.getFilterChain().addLast("logger", new LoggingFilter());
	    connector.setHandler(new ClientSessionHandler(atrss));
	    IoSession session;

	    for (;;) {//相当�?while(true){}
	        try {
	            ConnectFuture future = connector.connect(new InetSocketAddress(HOSTNAME, PORT));
	            future.awaitUninterruptibly();
	            session = future.getSession();
	            break;
	        } catch (RuntimeIoException e) {
	        	System.err.println("Failed to connect.");
	            e.printStackTrace();
	            Thread.sleep(5000);
	        }
	    }

	    // wait until the summation is done
	    session.getCloseFuture().awaitUninterruptibly();
	    connector.dispose();
	}
}
