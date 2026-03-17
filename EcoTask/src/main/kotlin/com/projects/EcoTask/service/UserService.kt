package com.projects.EcoTask.service

import com.projects.EcoTask.model.User
import com.projects.EcoTask.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Service layer responsible for handling business logic related to users.
 *
 * This class acts as an intermediary between the controller layer
 * and the repository layer.
 *
 * It manages operations such as:
 * - retrieving users
 * - validating user data
 * - coordinating user-related processes
 *
 * By separating business logic from controllers, the service layer
 * helps keep the application modular, maintainable, and easier to test.
 */

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun createUser(name: String, email: String, password : String): User {

        if (userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("This email already have an account")
        }

        val user = User(
            username = name,
            email = email,
            passwordValidation = password // mudar para um token criado com sha26 para não ter a passe guardada na BD
            //todo
        )

        return userRepository.save(user)
    }


    fun getUserByName(name: String): User?{
        return userRepository.getUserByName(name)
    }

    fun getUser(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }
}