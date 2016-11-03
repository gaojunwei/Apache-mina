package com.gjw.udp.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

public class MemoryMonitor {
	public static int PORT = 9123;
    public static void main( String[] args ) throws IOException
    {
    	NioDatagramAcceptor acceptor = new NioDatagramAcceptor();
    	
    	DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
    	chain.addLast("logger", new LoggingFilter());
    	acceptor.setHandler(new MemoryMonitorHandler());
    	
    	DatagramSessionConfig dcfg = acceptor.getSessionConfig();
    	dcfg.setReuseAddress(true);acceptor.bind(new InetSocketAddress(PORT));
    	
       /* acceptor.getSessionConfig().setReadBufferSize( 2048 );
        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );
        acceptor.bind( new InetSocketAddress(PORT) );*/
        System.out.println("·þÎñÆ÷Æô¶¯...");
    }
}
