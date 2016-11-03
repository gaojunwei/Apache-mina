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
	 * 功能描述：当任何由用户IoHandler或mina抛出异常时调用此方法，如果异常是IOException，Mina将自动关闭连接。
	 * 作者：gjw
	 */
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
	}

	/**
	 * 功能描述：接收到信息后，调用此方法
	 * 作者：gjw
	 */
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("message："+message.toString());
	}

}
