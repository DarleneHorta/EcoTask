package com.projects.EcoTask.model

import jakarta.persistence.*

/**
 * Represents a category of rewards available in the EcoTasks system.
 *
 * Reward categories allow the platform to organize different types
 * of rewards that users can earn, such as:
 * - eco points
 * - badges
 * - achievements
 *
 * Each reward belongs to a single reward category.
 */
@Entity
@Table(name = "rewardCategory", schema = "dbo")
data class RewardCategory (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "rewardCategory", nullable = false, unique = true, length = 256)
    val rewardCategory: String,

    @OneToMany(mappedBy = "rewardCategory", fetch = FetchType.LAZY)
    val rewards: List<Reward> = emptyList()
)