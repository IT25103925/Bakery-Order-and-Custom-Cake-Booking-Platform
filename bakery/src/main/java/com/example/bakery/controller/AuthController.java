package com.example.bakery.controller;

import com.example.bakery.model.User;
import com.example.bakery.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * AuthController - Member 01: Login, Register, Logout.
 * Spring MVC Controller mapping HTTP requests to views.
 */
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session, Model model) {
        if (session.getAttribute("userId") != null) {
            String role = (String) session.getAttribute("userRole");
            return "STAFF".equals(role) ? "redirect:/staff/dashboard" : "redirect:/customer/dashboard";
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session, Model model) {
        User user = userService.login(username, password);
        if (user == null) {
            model.addAttribute("error", "Invalid username or password.");
            return "auth/login";
        }
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("userRole", user.getRole());
        // Polymorphism: getDashboardPath() returns correct path per user type
        return "redirect:" + user.getDashboardPath();
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String email,
                              @RequestParam String phone,
                              @RequestParam String address,
                              Model model) {
        boolean success = userService.registerCustomer(username, password, email, phone, address);
        if (!success) {
            model.addAttribute("error", "Username already exists. Please choose another.");
            return "auth/register";
        }
        model.addAttribute("success", "Registration successful! Please login.");
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/customer/dashboard")
    public String customerDashboard(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        model.addAttribute("username", session.getAttribute("username"));
        return "auth/customer-dashboard";
    }

    @GetMapping("/staff/dashboard")
    public String staffDashboard(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        if (!"STAFF".equals(session.getAttribute("userRole"))) return "redirect:/customer/dashboard";
        model.addAttribute("username", session.getAttribute("username"));
        return "auth/staff-dashboard";
    }
}
