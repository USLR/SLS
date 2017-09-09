package online.pizzacrust.rserver.protocol;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.pizzacrust.rserver.ServerRegistrationsResponse;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

public class RegisterServerHandler extends AbstractHandler {

    public static String CODE;

    @Override
    public void handle(String s, Request implRequest, HttpServletRequest apiRequest,
                       HttpServletResponse apiResponse) throws IOException, ServletException {
        if (!apiRequest.getMethod().toLowerCase().equalsIgnoreCase("post")) {
            apiResponse.setStatus(SC_METHOD_NOT_ALLOWED);
            implRequest.setHandled(true);
            return;
        }
        Collection<String> key = apiRequest.getParameterMap().keySet();
        if (!key.contains("code") || !key.contains("instance_id") || !key.contains("regpc") ||
                !key.contains("place_id")) {
            apiResponse.setStatus(SC_BAD_REQUEST);
            implRequest.setHandled(true);
            return;
        }
        ServerRegistrationsResponse registrationsResponse = new ServerRegistrationsResponse();
        if (!apiRequest.getParameter("code").equalsIgnoreCase(CODE)) {
            apiResponse.setStatus(SC_UNAUTHORIZED);
            implRequest.setHandled(true);
            return;
        }
        registrationsResponse.placeId = Integer.parseInt(apiRequest.getParameter("place_id"));
        registrationsResponse.serverId = apiRequest.getParameter("instance_id");
        registrationsResponse.registeredPlayerCount = Integer.parseInt(apiRequest.getParameter
                ("regpc"));
        ServerRegistrationsResponse.getRegistry().servers.add(registrationsResponse);
        apiResponse.setStatus(SC_OK);
        implRequest.setHandled(true);
    }
}
