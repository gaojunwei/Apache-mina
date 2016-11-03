package com.gjw.udp.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MemoryMonitorHandler extends IoHandlerAdapter{
	

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("服务器收到信息："+message.toString());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("客户端关闭...");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("客户端接入...");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("Session idle...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("Session Opened...");
	}
	
}
