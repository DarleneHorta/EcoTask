package com.projects.EcoTask.model

import jakarta.persistence.*
import java.sql.Date

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

    @Column(name = "taskTypeId", nullable = false)
    val taskTypeId: Long,

    @Column(name = "taskDueDate", nullable = false)
    val taskDueDate: Long,

    @Column(name = "taskCreatedAt", nullable = false)
    val taskCreatedAt: Long
)