package com.projects.EcoTask.model

import jakarta.persistence.*

@Entity
@Table(name = "users", schema = "dbo")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, length = 64)
    val username: String,

    @Column(name = "password_validation", nullable = false, length = 256)
    val passwordValidation: String
)