/*
 * zenith : org.blarty.zenith.messaging.routers
 * 
 * 
 * MessageFilter.java
 * Created on 01-Apr-2005
 * 
 * MessageFilter
 *
 */

package org.blarty.zenith.messaging.components.routers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.blarty.zenith.messaging.channels.PublishingChannel;
import org.blarty.zenith.messaging.channels.ReceiverChannel;
import org.blarty.zenith.messaging.channels.connectors.ReceivingQConnector;
import org.blarty.zenith.messaging.messages.Message;
import org.blarty.zenith.messaging.system.ChannelException;
import org.blarty.zenith.messaging.system.MessagingManager;

/**
 * @author calum
 */
public class MessageFilter {
	String inputChannelName;
	Filter theFilter;
	String outputChannelName;
	private Logger log = Logger.getLogger("org.blarty.zenith.messaging.routers");

	public MessageFilter(ReceiverChannel inputChannel, PublishingChannel outputChannel, Filter theFilter) {
		try {
			inputChannelName = inputChannel.getName();
			outputChannelName = outputChannel.getName();
			buildSyncCheckThread();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error binding MessageFilter", e);
		}
	}

	private void buildSyncCheckThread() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				// TODO Complete method stub for run
				ReceivingQConnector rec = null;
				try {
					rec = MessagingManager.getManager().registerOnChannel(inputChannelName, null);
				} catch (ChannelException e) {
					// URGENT Handle ChannelException
					e.printStackTrace();
				}
				for (;;) {
					Message m = rec.receive(1000);
				//	boolean consumed = false;
					if (m != null)
						if (theFilter.check(m)) {
							try {
								PublishingChannel ch = MessagingManager.getManager().getSendingChannel(outputChannelName);
								ch.getPublishingQConnector().sendMessage(m);
							} catch (Exception ex) {
								log.log(Level.SEVERE, "Error sending Message", ex);
							}
						}
				}
			}
		});
		t.start();
	}

}
