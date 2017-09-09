package online.pizzacrust.rserver;

import java.util.ArrayList;
import java.util.List;

public class ServerRegistrationsResponse {

    private static ServerRegistry registry;

    public static ServerRegistry getRegistry() {
        if (registry == null) {
            registry = new ServerRegistry();
            registry.servers = new ArrayList<>();
        }
        return registry;
    }

    public static class ServerRegistry {

        public List<ServerRegistrationsResponse> servers;

    }

    public int placeId;
    public String serverId;
    public int registeredPlayerCount;

}
