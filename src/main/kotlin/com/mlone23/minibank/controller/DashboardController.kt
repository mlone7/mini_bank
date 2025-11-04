package com.mlone23.minibank.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DashboardController {

    @GetMapping("/dashboard")
    fun showDashboard (): String {
        return "dashboard"
    }
}