package com.projects.EcoTask.security

import com.projects.EcoTask.repository.UserRepository
import org.springframework.security.core.userdetails.*
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val user = userRepository.getUserByName(username)
            ?: throw UsernameNotFoundException("User not found")

        return User(
            user.username,
            user.passwordValidation,
            emptyList()
        )
    }
}