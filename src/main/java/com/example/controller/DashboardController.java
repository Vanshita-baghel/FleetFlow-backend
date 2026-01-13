package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.DashboardResponseDto;
import com.example.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
// @CrossOrigin(origins = "http://localhost:5173")
public class DashboardController{

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public DashboardResponseDto getDashboard(){
        return dashboardService.getDashboard();
    }
    
}