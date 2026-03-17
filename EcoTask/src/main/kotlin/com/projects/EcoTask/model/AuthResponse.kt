package com.projects.EcoTask.model


/**
 * Represents the response returned by the API after an authentication operation.
 *
 * This class is used to inform the client about the result of authentication
 * actions such as login or user registration.
 *
 * In future implementations it may also include additional data such as
 * authentication tokens (e.g., JWT).
 */
data class AuthResponse(
    val message: String
)