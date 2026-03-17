package com.projects.EcoTask.model

/**
 * Represents a request to create a new user account.
 *
 * This class is used when a new user wants to register in the EcoTasks system.
 *
 * It contains the minimum required information to create a user,
 * typically including a username and password.
 */
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)
