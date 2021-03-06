package org.blarty.zenith.messaging.test;

import java.rmi.RMISecurityManager;

import net.jini.config.ConfigurationProvider;

import org.blarty.zenith.messaging.channels.MessageChannel;
import org.blarty.zenith.messaging.channels.ReceiverChannel;
import org.blarty.zenith.messaging.components.ControllableComponent;
import org.blarty.zenith.messaging.components.DefaultControllableComponent;
import org.blarty.zenith.messaging.system.MessagingManager;

public class RecipientListTest {

        /**
         * @param args
         */
        public static void main(String[] args) throws Exception {
                // TODO Auto-generated method stub
                System.out.println("Starting test Harness");
                System.setProperty("org.blarty.zenith.messaging.system.store.dir", "zenithlogs");
                System.setSecurityManager(new RMISecurityManager());
                MessagingManager.createManager("default", ConfigurationProvider.getInstance(new String[]{"conf/messagingMgr.config"}));
                MessagingManager mgr = MessagingManager.getManager();
                MessageChannel a_channel = mgr.createChannel("A");
                MessageChannel b_channel = mgr.createChannel("B");
                ControllableComponent ce = new DefaultControllableComponent();
                ce.setInputChannel((ReceiverChannel)a_channel);
                Thread.sleep(2000);
                ce.setInputChannel((ReceiverChannel)b_channel);
                
        }

}
