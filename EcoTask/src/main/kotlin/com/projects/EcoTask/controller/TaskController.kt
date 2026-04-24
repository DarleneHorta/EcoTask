package com.projects.EcoTask.controller

import com.projects.EcoTask.model.CreateTaskRequest
import com.projects.EcoTask.model.CustomUserPrincipal
import com.projects.EcoTask.model.TaskResponse
import com.projects.EcoTask.model.UpdateTaskRequest
import com.projects.EcoTask.model.toResponse
import com.projects.EcoTask.service.TaskService
import org.springframework.web.bind.annotation.*

import org.slf4j.LoggerFactory
import org.springframework.security.core.annotation.AuthenticationPrincipal


@RestController
@RequestMapping("/tasks")
class TaskController(
    private val taskService: TaskService
) {

    private val logger = LoggerFactory.getLogger(TaskController::class.java)

    @PostMapping
    fun createTask(
        @AuthenticationPrincipal user: CustomUserPrincipal,
        @RequestBody request: CreateTaskRequest
    ): TaskResponse {

        logger.info("POST /tasks - Creating task for userId={}", user.id)

        val task = taskService.createTask(user.id, request)

        logger.info("Task created id={} for userId={}", task.id, user.id)

        return task.toResponse()
    }

    @GetMapping
    fun getTasks(
        @AuthenticationPrincipal user: CustomUserPrincipal,
    ) = run {

        logger.info("GET /tasks - userId={}", user.id)

        val result = taskService.getTasks(user.id)
            .map { it.toResponse() }

        logger.info("Returned {} tasks for userId={}", result.size , user.id)

        result
    }

    @PutMapping("/{taskId}")
    fun updateTask(
        @AuthenticationPrincipal user: CustomUserPrincipal,
        @PathVariable taskId: Long,
        @RequestBody request: UpdateTaskRequest
    ): TaskResponse {

        logger.info("PUT /tasks/{} - userId={}", taskId, user.id)

        val updated = taskService.updateTask(taskId, request)

        logger.info("Task {} updated for userId={}", taskId, user.id)

        return updated.toResponse()
    }

    @PatchMapping("/{taskId}/complete")
    fun completeTask(
        @AuthenticationPrincipal user: CustomUserPrincipal,
        @PathVariable taskId: Long
    ): TaskResponse {

        logger.info("PATCH /tasks/{}/complete - userId={}", taskId, user.id)

        val updated = taskService.markAsCompleted( taskId)

        return updated.toResponse()
    }

    @DeleteMapping("/{taskId}")
    fun deleteTask(
        @AuthenticationPrincipal user: CustomUserPrincipal,
        @PathVariable taskId: Long
    ) {

        logger.info("DELETE /tasks/{} - userId={}", taskId, user.id)

        taskService.deleteTask(taskId)

        logger.info("Task {} deleted for userId={}", taskId, user.id)
    }
}