package com.example.servlets;

import com.example.repository.SightingRepository;
import com.example.service.SightingService;
import com.example.model.Sighting;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sightings")
public class SightingServlet extends HttpServlet {
    private SightingService sightingService;

    @Override
    public void init() {
        sightingService = com.example.Application.getContext()
                .getBean("sightingService", SightingService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("<h1>Game Sessions registered</h1>");

        resp.getWriter().println("<ul>");
        for (Sighting sighting : sightingService.getsightings()) {
            resp.getWriter().println("<li>" + sighting + "</li>");
        }
        resp.getWriter().println("</ul>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        try {
            Sighting sighting = new Sighting(
                    Integer.parseInt(req.getParameter("id")),
                    req.getParameter("sightingCode"),
                    req.getParameter("name"),
                    req.getParameter("description"),
                    req.getParameter("scientificName"),
                    req.getParameter("sightedAt"),
                    req.getParameter("location"),
                    Integer.parseInt(req.getParameter("quantity")),
                    Integer.parseInt(req.getParameter("confidenceLevel")),
                    Integer.parseInt(req.getParameter("expeditionId")));
            boolean added = sightingService.addsighting(sighting);

            resp.setContentType("text/html; charset=UTF-8");
            if (added) {
                resp.getWriter().println("<h1>Sighting added successfully</h1>");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("<h1>Failed to add sighting</h1>");
            }
        } catch (NumberFormatException exception) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Numeric fields must contain valid integers.");
        }
    }
}
