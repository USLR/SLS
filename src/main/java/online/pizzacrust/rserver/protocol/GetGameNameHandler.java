package online.pizzacrust.rserver.protocol;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;

public class GetGameNameHandler extends AbstractHandler {
    @Override
    public void handle(String s, Request implRequest, HttpServletRequest apiRequest,
                       HttpServletResponse apiResponse) throws IOException, ServletException {
        Collection<String> key = apiRequest.getParameterMap().keySet();
        if (!key.contains("game_id")) {
            apiResponse.setStatus(SC_BAD_REQUEST);
            implRequest.setHandled(true);
            return;
        }
        String link = "https://www.roblox.com/games/" + apiRequest.getParameter("game_id")
                + "/none";
        Document document = Jsoup.connect(link).get();
        Elements elements = document.getElementsByClass("game-name");
        if (elements.size() < 1) {
            apiResponse.setStatus(SC_BAD_REQUEST);
            implRequest.setHandled(true);
            return;
        }
        String title  = elements.first().attr("title");
        apiResponse.setStatus(SC_OK);
        apiResponse.getWriter().println(title);
        implRequest.setHandled(true);
    }
}
