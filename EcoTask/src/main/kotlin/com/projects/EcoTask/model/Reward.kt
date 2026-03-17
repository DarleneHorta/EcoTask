package com.projects.EcoTask.model

import jakarta.persistence.*


/**
 * Represents a reward granted to a user for completing tasks
 * in the EcoTasks system.
 *
 * Rewards help motivate users by recognizing their contributions
 * to environmentally friendly activities.
 *
 * Each reward is associated with:
 * - a user
 * - a reward category
 * - the date the reward was granted
 */
@Entity
@Table(name = "reward", schema = "dbo")
data class Reward (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "rewardDate", nullable = false)
    val rewardDate: Long,

    @Column(name = "rewardCategoryId", nullable = false)
    val rewardCategoryId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rewardCategoryId", nullable = false)
    val rewardCategory: RewardCategory

)