package com.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.model.Expedition;
import com.example.service.ExpeditionService;

import java.io.IOException;

@WebServlet("/expeditions")
public class ExpeditionServlet extends HttpServlet {
    private ExpeditionService expeditionService;

    @Override
    public void init() {
        expeditionService = com.example.Application.getContext()
                .getBean("expeditionService", ExpeditionService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("<h1>Game Sessions registered</h1>");

        resp.getWriter().println("<ul>");
        for (Expedition expedition : expeditionService.getExpeditions()) {
            resp.getWriter().println("<li>" + expedition + "</li>");
        }
        resp.getWriter().println("</ul>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        try {
            Expedition expedition = new Expedition(
                    Integer.parseInt(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("code"),
                    req.getParameter("region"),
                    req.getParameter("baseCamp"),
                    req.getParameter("leader"),
                    req.getParameter("starDate"),
                    req.getParameter("endDate"),
                    req.getParameter("state"));
            boolean added = expeditionService.addExpedition(expedition);

            resp.setContentType("text/html; charset=UTF-8");
            if (added) {
                resp.getWriter().println("<h1>Expedition added successfully</h1>");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("<h1>Failed to add Expeditions</h1>");
            }
        } catch (NumberFormatException exception) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Numeric fields must contain valid integers.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            respondToDelete(id, resp);
        } catch (NumberFormatException exception) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("The Expedition ID must be a valid integer.");
        }

    }

    private void respondToDelete(int id, HttpServletResponse resp) throws IOException {
        if (!expeditionService.deleteExpedition(id)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("The Expedition does not exist or has associated sightings.");
            return;
        }
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
