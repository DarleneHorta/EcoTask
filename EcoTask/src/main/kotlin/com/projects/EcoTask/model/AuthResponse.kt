package com.projects.EcoTask.model


/**
 * Represents the response returned by the API after an authentication operation.
 *
 * This class is used to inform the client about the result of authentication
 * actions such as login or user registration.
 *
 */
data class AuthResponse(
    val message: String,
    val token: String? = null
)