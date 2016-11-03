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
		System.out.println("�������յ���Ϣ��"+message.toString());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("�ͻ��˹ر�...");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("�ͻ��˽���...");
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
