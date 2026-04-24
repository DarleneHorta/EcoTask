package com.projects.EcoTask.security


import com.projects.EcoTask.model.CustomUserPrincipal
import jakarta.servlet.FilterChain
import jakarta.servlet.http.*
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    private val logger = LoggerFactory.getLogger(JwtAuthFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.substring(7)

        try {
            val claims = jwtService.getClaims(token)

            val userId = (claims["userId"] as Number).toLong()
            val username = claims.subject

            val userDetails = CustomUserPrincipal(
                id = userId,
                usernameValue = username,
                passwordValue = ""
            )

            val authToken = UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.authorities
            )

            SecurityContextHolder.getContext().authentication = authToken

            logger.debug("Authenticated userId={} username={}", userId, username)

        } catch (ex: Exception) {
            logger.warn("Invalid JWT token: {}", ex.message)
        }

        filterChain.doFilter(request, response)
    }
}