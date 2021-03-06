/*
 * apollo2 : apollo.messaging.channels.connectors.transports.jini
 * 
 * 
 * JiniPublishingQConnector.java
 * Created on 26-Feb-2004
 * 
 * JiniPublishingQConnector
 *
 */
package org.blarty.zenith.messaging.channels.connectors.transports.jini;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.blarty.zenith.messaging.messages.Message;


/**
 * @author calum
 */
public interface RemotePublishingQConnector extends Remote{
     public void sendMessage(Message m) throws RemoteException;
}
