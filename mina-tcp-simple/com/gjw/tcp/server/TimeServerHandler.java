package com.gjw.tcp.server;

import java.util.Date;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeServerHandler extends IoHandlerAdapter
{
	/**
	 * 功能描述：当任何由用户IoHandler或mina抛出异常时调用此方法，如果异常是IOException，Mina将自动关闭连接。
	 * 作者：gjw
	 */
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
    {
        cause.printStackTrace();
    }
    @Override
    /**
     * 功能描述：当消息被接收后调用此方法。
     * 作者：gjw
     */
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
        String str = message.toString();
        System.out.println("服务器收到信息："+str);
        if( str.trim().equalsIgnoreCase("quit") ) {
            session.close(true);
            return;
        }
        Date date = new Date();
        session.write( date.toString() );
        System.out.println("Message written...");
    }
    
    /**
	 * 功能描述：当连接空闲时触发此方法。
	 * 作者：gjw
	 */
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
        System.out.println( "当连接空闲session：" + session.getIdleCount( status ));
    }
    
	/**
	 * 功能描述：当连接被关闭时触发，例如客户端程序意外退出等等。
	 * 作者：gjw
	 */
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("客户端关闭");
	}
	
	/**
	 * 功能描述：当一个新客户端连接后触发此方法.
	 * 作者：gjw
	 */
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("有客户端连接进来");
	}
    
}