package com.projects.EcoTask.model

import com.projects.EcoTask.repository.TaskRepository
import jakarta.persistence.*
import java.sql.Date

/**
 * Represents a task that a user needs to complete.
 *
 * Tasks describe environmentally friendly actions that users
 * can perform within the EcoTasks platform.
 *
 * Each task includes information such as:
 * - task name
 * - description
 * - task type
 * - due date
 * - creation date
 *
 * A task belongs to a specific user and is associated with
 * a particular task type.
 */
@Entity
@Table(name = "tasks", schema = "dbo")
data class Tasks(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "taskName", nullable = false, length = 64)
    val taskName: String,

    @Column(name = "taskDesc", length = 256)
    val taskDesc: String? = null,

    @Column(name = "isCompleted", length = 256)
    val isCompleted: Boolean = false,

    @Column(name = "taskDueDate", nullable = false)
    val taskDueDate: Long,

    @Column(name = "taskCreatedAt", nullable = false)
    val taskCreatedAt: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskType", nullable = false)
    val taskType: String
)

data class CreateTaskRequest(
    val taskName: String,
    val taskDesc: String?,
    val taskTypeId: Long,
    val dueDate: Long
)

data class UpdateTaskRequest(
    val taskName: String?,
    val taskDesc: String?,
    val dueDate: Long?
)

data class TaskResponse(
    val id: Long,
    val taskName: String,
    val taskDesc: String?,
    val dueDate: Long,
    val createdAt: Long,
    val completed: Boolean,
    val taskType: String
)

fun Tasks.toResponse(): TaskResponse {
    return TaskResponse(
        id = id,
        taskName = taskName,
        taskDesc = taskDesc,
        dueDate = taskDueDate,
        createdAt = taskCreatedAt,
        completed = isCompleted,
        taskType = taskType
    )
}