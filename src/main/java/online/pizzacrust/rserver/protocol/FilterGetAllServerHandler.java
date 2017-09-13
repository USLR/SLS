package online.pizzacrust.rserver.protocol;

import com.google.gson.Gson;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.pizzacrust.rserver.ServerRegistrationsResponse;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;

public class FilterGetAllServerHandler extends AbstractHandler { // r
    @Override
    public void handle(String s, Request implRequest, HttpServletRequest apiRequest,
                       HttpServletResponse
            apiResponse) throws IOException, ServletException {
        Collection<String> key = apiRequest.getParameterMap().keySet();
        if (!key.contains("game_id")) {
            apiResponse.setStatus(SC_BAD_REQUEST);
            implRequest.setHandled(true);
            return;
        }
        apiResponse.setContentType("application/json");
        apiResponse.setStatus(SC_OK);
        ServerRegistrationsResponse.ServerRegistry unFiltered = ServerRegistrationsResponse
                .getRegistry();
        ServerRegistrationsResponse.ServerRegistry filtered = new ServerRegistrationsResponse
                .ServerRegistry();
        List<ServerRegistrationsResponse> responses = new ArrayList<>();
        int specifiedId = Integer.parseInt(apiRequest.getParameter("game_id"));
        unFiltered.servers.forEach((r) -> {
            if (specifiedId == r.placeId) {
                responses.add(r);
            }
        });
        filtered.servers = responses;
        String json = new Gson().toJson(filtered.servers);
        apiResponse.getWriter().println(json);
        implRequest.setHandled(true);
    }
}
