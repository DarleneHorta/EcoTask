package com.projects.EcoTask.controller

import com.projects.EcoTask.model.AuthResponse
import com.projects.EcoTask.model.LoginRequest
import com.projects.EcoTask.model.RegisterRequest
import com.projects.EcoTask.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<AuthResponse> {

        authService.register(request)

        return ResponseEntity.ok(
            AuthResponse("User registered successfully")
        )
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {

        authService.login(request)

        return ResponseEntity.ok(
            AuthResponse("Login successful")
        )
    }
}