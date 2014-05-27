package models;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;

import java.util.HashMap;

public class Channels {
    public static class ChannelNotFound extends Exception {};
    private static final HashMap<String, LLPConnector> channels;

    static {
        channels = new HashMap<String, LLPConnector>();
        channels.put("dev", new LLPConnector("localhost", 57986));
    }

    public static Connection get(String channelKey) throws ChannelNotFound, HL7Exception {
        if (!channels.containsKey(channelKey)) { throw new ChannelNotFound(); }

        LLPConnector connector = channels.get(channelKey);
        HapiContext context = new DefaultHapiContext();
        return context.newClient(connector.hostname, connector.port, false);
    }

}
