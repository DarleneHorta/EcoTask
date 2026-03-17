package com.projects.EcoTask.model

import jakarta.persistence.*

/**
 * Represents a registered user in the EcoTasks system.
 *
 * Each user can create and manage tasks and receive rewards
 * for completing environmentally friendly activities.
 *
 * This entity maps to the "users" table in the database and
 * stores authentication and identification information.
 */
@Entity
@Table(name = "users", schema = "dbo")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, length = 64)
    val username: String,

    @Column(nullable = false, unique = true, length = 256)
    val email: String,

    @Column(name = "password_validation", nullable = false, length = 256)
    val passwordValidation: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val rewards: List<Reward> = emptyList(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val tasks: List<Tasks> = emptyList()
)