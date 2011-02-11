package org.blarty.zenith.messaging.system;

import org.blarty.zenith.messaging.channels.PublishingChannel;
import org.blarty.zenith.messaging.channels.ReceiverChannel;
import org.blarty.zenith.messaging.messages.Message;

public interface ThroughChannelAction {
        public boolean dispatch(Message m, ReceiverChannel input, PublishingChannel output, PublishingChannel invalid);
}
