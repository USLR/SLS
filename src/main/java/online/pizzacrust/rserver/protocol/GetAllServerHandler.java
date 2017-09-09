package online.pizzacrust.rserver.protocol;

import com.google.gson.Gson;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.pizzacrust.rserver.ServerRegistrationsResponse;

import static javax.servlet.http.HttpServletResponse.*;

public class GetAllServerHandler extends AbstractHandler {

    public void handle(String s, Request implRequest, HttpServletRequest apiRequest,
                       HttpServletResponse apiResponse) throws IOException, ServletException {
        apiResponse.setContentType("application/json");
        apiResponse.setStatus(SC_OK);
        String json = new Gson().toJson(ServerRegistrationsResponse.getRegistry());
        apiResponse.getWriter().println(json);
        implRequest.setHandled(true);
    }

}
