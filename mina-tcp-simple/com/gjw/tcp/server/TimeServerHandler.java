package com.gjw.tcp.server;

import java.util.Date;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeServerHandler extends IoHandlerAdapter
{
	/**
	 * �������������κ����û�IoHandler��mina�׳��쳣ʱ���ô˷���������쳣��IOException��Mina���Զ��ر����ӡ�
	 * ���ߣ�gjw
	 */
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
    {
        cause.printStackTrace();
    }
    @Override
    /**
     * ��������������Ϣ�����պ���ô˷�����
     * ���ߣ�gjw
     */
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
        String str = message.toString();
        System.out.println("�������յ���Ϣ��"+str);
        if( str.trim().equalsIgnoreCase("quit") ) {
            session.close(true);
            return;
        }
        Date date = new Date();
        session.write( date.toString() );
        System.out.println("Message written...");
    }
    
    /**
	 * ���������������ӿ���ʱ�����˷�����
	 * ���ߣ�gjw
	 */
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
        System.out.println( "�����ӿ���session��" + session.getIdleCount( status ));
    }
    
	/**
	 * ���������������ӱ��ر�ʱ����������ͻ��˳��������˳��ȵȡ�
	 * ���ߣ�gjw
	 */
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("�ͻ��˹ر�");
	}
	
	/**
	 * ������������һ���¿ͻ������Ӻ󴥷��˷���.
	 * ���ߣ�gjw
	 */
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("�пͻ������ӽ���");
	}
    
}