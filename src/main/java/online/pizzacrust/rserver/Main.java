package online.pizzacrust.rserver;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import online.pizzacrust.rserver.protocol.GetAllServerHandler;
import online.pizzacrust.rserver.protocol.RegisterServerHandler;

public class Main {

    public static void main(String... args) throws Exception {
        Server server = new Server(Integer.parseInt(args[0]));
        System.out.println("port " + args[0]);
        ContextHandler getAll = new ContextHandler();
        getAll.setContextPath("/getallservers");
        getAll.setHandler(new GetAllServerHandler());
        ContextHandler regServerHandler = new ContextHandler();
        regServerHandler.setContextPath("/registerserver");
        regServerHandler.setHandler(new RegisterServerHandler());
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { getAll, regServerHandler });
        server.setHandler(contexts);
        server.start();
        server.join();
    }

}
