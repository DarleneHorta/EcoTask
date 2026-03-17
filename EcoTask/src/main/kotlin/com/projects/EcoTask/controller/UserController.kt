package com.projects.EcoTask.controller

import com.projects.EcoTask.model.User
import com.projects.EcoTask.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller responsible for handling HTTP requests related to users.
 *
 * This controller exposes endpoints that allow clients to interact
 * with user resources in the EcoTasks system.
 *
 * Typical operations handled by this controller include:
 * - retrieving users
 * - creating new users
 * - managing user-related data
 *
 * The controller communicates with the UserService to process
 * requests and return appropriate HTTP responses.
 */

@RestController
@RequestMapping("/user")
class UserController(
   val userRepository: UserRepository
)
{
    private val logger = LoggerFactory.getLogger(UserController::class.java)
    private val baseUrl = System.getenv("BASE_URL")

    @GetMapping("/{name}")
    fun getUserByName(@PathVariable name : String): ResponseEntity<User?>{
        logger.info("Getting User $name")
        val user = userRepository.getUserByName(name)
        if(user != null){
            logger.info("Found User $name and user id is ${user.id}")
            return ResponseEntity(user, HttpStatus.OK)
        }
        logger.info("User $name not found")
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }



}