package com.projects.EcoTask.security

import com.projects.EcoTask.model.User
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {

    private val secret = "my-super-secret-key-my-super-secret-key"
    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.username)
            .claim("userId", user.id)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(key)
            .compact()
    }

    fun extractUsername(token: String): String {
        return getClaims(token).subject
    }

    fun isValid(token: String, username: String): Boolean {
        val extracted = extractUsername(token)
        return extracted == username && !isExpired(token)
    }

    private fun isExpired(token: String): Boolean {
        return getClaims(token).expiration.before(Date())
    }

    fun getClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}