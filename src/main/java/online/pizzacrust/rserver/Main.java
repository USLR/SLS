package online.pizzacrust.rserver;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import java.util.UUID;

import online.pizzacrust.rserver.protocol.GetAllServerHandler;
import online.pizzacrust.rserver.protocol.RegisterServerHandler;
import online.pizzacrust.rserver.protocol.ShutdownServerHandler;

import static online.pizzacrust.rserver.protocol.RegisterServerHandler.CODE;

public class Main { //mmm

    public static void main(String... args) throws Exception {
        Server server = new Server(Integer.parseInt(args[0]));
        System.out.println("port " + args[0]);
        CODE = UUID.randomUUID().toString().replace("-", "");
        System.out.println("code " + CODE);
        ContextHandler getAll = new ContextHandler();
        getAll.setContextPath("/getallservers");
        getAll.setHandler(new GetAllServerHandler());
        ContextHandler regServerHandler = new ContextHandler();
        regServerHandler.setContextPath("/registerserver");
        regServerHandler.setHandler(new RegisterServerHandler());
        ContextHandler shutdownHandler = new ContextHandler();
        shutdownHandler.setContextPath("/delserver");
        shutdownHandler.setHandler(new ShutdownServerHandler());
        ContextHandler getNameHandler = new ContextHandler();
        getNameHandler.setContextPath("/getname");
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { getAll, regServerHandler, shutdownHandler, getNameHandler });
        server.setHandler(contexts);
        server.start();
        server.join();
    }

}
