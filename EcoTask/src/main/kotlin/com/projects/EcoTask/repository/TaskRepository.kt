package com.projects.EcoTask.repository

import com.projects.EcoTask.model.TaskType
import com.projects.EcoTask.model.Tasks
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Date


interface TaskRepository : JpaRepository<Tasks, Long> {

    fun findByUserEmail(email: String): List<Tasks>

    fun findByTaskName(taskName: String): List<Tasks>

    fun findByTaskType(taskType: TaskType): List<Tasks>

    fun findByUserId(userId: Long): List<Tasks>
}