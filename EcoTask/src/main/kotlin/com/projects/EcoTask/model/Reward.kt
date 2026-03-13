package com.projects.EcoTask.model

import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "reward", schema = "dbo")
data class Reward (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "userId", nullable = false)
    val userId: Long,

    @Column(name = "rewardDate", nullable = false)
    val rewardDate: Long,

    @Column(name = "rewardCategoryId", nullable = false)
    val rewardCategoryId: Long

)