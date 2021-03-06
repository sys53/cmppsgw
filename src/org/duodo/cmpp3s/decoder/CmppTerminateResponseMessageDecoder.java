/**
 * 
 */
package org.duodo.cmpp3s.decoder;

import org.duodo.cmpp3s.message.CmppTerminateResponseMessage;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppTerminateResponseMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	
	public CmppTerminateResponseMessageDecoder() {
		this(CmppPacketType.CMPPTERMINATERESPONSE);
	}

	public CmppTerminateResponseMessageDecoder(PacketType packetType) {
		this.packetType = packetType;
	}
	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		
		Message message = (Message) msg;
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(packetType.getCommandId() != commandId) return msg;
        
        CmppTerminateResponseMessage responseMessage = new CmppTerminateResponseMessage();
        responseMessage.setBodyBuffer(message.getBodyBuffer());
        responseMessage.setHeader(message.getHeader());
                       
		return responseMessage;
	}

}
