package com.projects.EcoTask.service

import com.projects.EcoTask.model.User
import com.projects.EcoTask.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun createUser(name: String, email: String, password : String): User {

        if (userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("This email already have an account")
        }

        val user = User(
            username = name,
            email = email,
            passwordValidation = password // mudar para um token criado com sha26 para não ter a passe guardada na BD
        )

        return userRepository.save(user)
    }

    fun getUser(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }
}