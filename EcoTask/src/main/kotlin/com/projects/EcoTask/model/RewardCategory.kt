package com.projects.EcoTask.model

import jakarta.persistence.*

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