package com.gjw.tcp.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientSessionHandler extends IoHandlerAdapter {

	private final static Logger LOGGER = LoggerFactory.getLogger(ClientSessionHandler.class);
	
	private final String[] values;
	
	private boolean finished;
	
	public ClientSessionHandler(String[] values) {
		this.values = values;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		for (int i = 0; i < values.length; i++) {
			session.write(values[i]);
		}
	}

	/**
	 * �������������κ����û�IoHandler��mina�׳��쳣ʱ���ô˷���������쳣��IOException��Mina���Զ��ر����ӡ�
	 * ���ߣ�gjw
	 */
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
	}

	/**
	 * �������������յ���Ϣ�󣬵��ô˷���
	 * ���ߣ�gjw
	 */
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("message��"+message.toString());
	}

}
