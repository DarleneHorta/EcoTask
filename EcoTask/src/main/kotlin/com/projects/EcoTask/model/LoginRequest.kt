package com.projects.EcoTask.model

/**
 * Represents the authentication request sent by a client.
 *
 * This class contains the credentials required for a user to log in
 * to the EcoTasks system.
 *
 * It is typically used by the login endpoint and includes:
 * - username: the unique identifier of the user
 * - password: the user's plain text password
 */
data class LoginRequest(
    val username: String,
    val password: String
)