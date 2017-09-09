package online.pizzacrust.rserver.protocol;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.pizzacrust.rserver.ServerRegistrationsResponse;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static online.pizzacrust.rserver.protocol.RegisterServerHandler.CODE;

public class ShutdownServerHandler extends AbstractHandler {
    @Override
    public void handle(String s, Request implRequest, HttpServletRequest apiRequest,
                       HttpServletResponse apiResponse) throws IOException, ServletException {
        Collection<String> key = apiRequest.getParameterMap().keySet();
        if (!key.contains("code") || !key.contains("instance_id")) {
            apiResponse.setStatus(SC_BAD_REQUEST);
            implRequest.setHandled(true);
            return;
        }
        final ServerRegistrationsResponse[] registrationsResponse = new ServerRegistrationsResponse[1];
        if (!apiRequest.getParameter("code").equalsIgnoreCase(CODE)) {
            apiResponse.setStatus(SC_UNAUTHORIZED);
            implRequest.setHandled(true);
            return;
        }
        ServerRegistrationsResponse.getRegistry().servers.forEach((sr) -> {
            if (sr.serverId.equals(apiRequest.getParameter("instance_id"))) {
                registrationsResponse[0] = sr;
            }
        });
        if (registrationsResponse[0] != null) {
            ServerRegistrationsResponse.getRegistry().servers.remove(registrationsResponse[0]);
        }
        apiResponse.setStatus(SC_OK);
        implRequest.setHandled(true);
    }
}
