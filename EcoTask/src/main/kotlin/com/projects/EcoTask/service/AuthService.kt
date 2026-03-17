package com.projects.EcoTask.service

import com.projects.EcoTask.model.LoginRequest
import com.projects.EcoTask.model.RegisterRequest
import com.projects.EcoTask.model.User
import com.projects.EcoTask.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
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

    fun login(request: LoginRequest): User {

        val user = userRepository.getUserByName(request.username)
            ?: throw IllegalArgumentException("Invalid credentials")

        val passwordValid =
            passwordEncoder.matches(request.password, user.passwordValidation)

        if (!passwordValid) {
            throw IllegalArgumentException("Invalid credentials")
        }

        return user
    }
}