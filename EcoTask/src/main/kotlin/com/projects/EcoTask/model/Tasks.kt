package com.projects.EcoTask.model

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

    @Column(name = "taskDueDate", nullable = false)
    val taskDueDate: Long,

    @Column(name = "taskCreatedAt", nullable = false)
    val taskCreatedAt: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskTypeId", nullable = false)
    val taskType: TaskType
)