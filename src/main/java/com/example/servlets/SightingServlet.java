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

}
