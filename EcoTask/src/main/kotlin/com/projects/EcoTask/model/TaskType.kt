package com.projects.EcoTask.model

import jakarta.persistence.*

@Entity
@Table(name = "rewardCategory", schema = "dbo")
data class TaskType(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "taskType", nullable = false, unique = true, length = 256)
    val taskType: String
)