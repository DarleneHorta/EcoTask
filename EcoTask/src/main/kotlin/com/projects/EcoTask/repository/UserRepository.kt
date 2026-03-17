package com.projects.EcoTask.repository

import com.projects.EcoTask.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository responsible for accessing and managing User entities
 * in the database.
 *
 * This interface extends JpaRepository, which provides built-in
 * CRUD operations such as saving, deleting, and retrieving users.
 *
 * It also allows defining custom query methods based on method names,
 * for example retrieving users by their username.
 *
 * The repository acts as the data access layer between the
 * application services and the database.
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean

    fun existsByName(name: String): Boolean

    fun getUserByName(name: String): User?


}