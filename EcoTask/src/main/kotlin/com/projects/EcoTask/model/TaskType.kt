package com.projects.EcoTask.model

import jakarta.persistence.*

/**
 * Represents a category or type of task.
 *
 * Task types are used to classify tasks into different groups,
 * for example:
 * - work tasks
 * - daily tasks
 * - school tasks
 *
 * Each task in the system is associated with exactly one task type.
 */
@Entity
@Table(name = "rewardCategory", schema = "dbo")
data class TaskType(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "taskType", nullable = false, unique = true, length = 256)
    val taskType: String,

    @OneToMany(mappedBy = "taskType", fetch = FetchType.LAZY)
    val tasks: List<Tasks> = emptyList()
)