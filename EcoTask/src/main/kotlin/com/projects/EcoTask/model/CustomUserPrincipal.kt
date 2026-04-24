package com.projects.EcoTask.model

import org.springframework.security.core.*
import org.springframework.security.core.userdetails.UserDetails

class CustomUserPrincipal(
    val id: Long,
    private val usernameValue: String,
    private val passwordValue: String
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> = emptyList()

    override fun getPassword(): String = passwordValue

    override fun getUsername(): String = usernameValue

    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}