package com.projects.EcoTask.service

import com.projects.EcoTask.model.LoginRequest
import com.projects.EcoTask.model.RegisterRequest
import com.projects.EcoTask.model.User
import com.projects.EcoTask.repository.UserRepository
import com.projects.EcoTask.security.JwtService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {
    //  TODO
    // criar classes de exceções para lançar erros HTTP de acordo com o tipo de erro!!!

    fun register(request: RegisterRequest): User? {

        if (userRepository.existsByName(request.username)) {
            throw IllegalArgumentException("Username already exists")
        }

        val user = User(
            username = request.username,
            email = request.email,
            passwordValidation = passwordEncoder.encode(request.password)
        )

        return userRepository.save(user)
    }

    fun login(request: LoginRequest): String {

        val user = userRepository.getUserByName(request.username)
            ?: throw RuntimeException("Invalid credentials")

        if (!passwordEncoder.matches(request.password, user.passwordValidation)) {
            throw RuntimeException("Invalid credentials")
        }

        return jwtService.generateToken(user.username)
    }
}