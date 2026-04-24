package com.projects.EcoTask.service

import com.projects.EcoTask.model.CreateTaskRequest
import com.projects.EcoTask.model.TaskType
import com.projects.EcoTask.model.Tasks
import com.projects.EcoTask.model.UpdateTaskRequest
import com.projects.EcoTask.repository.TaskRepository
import com.projects.EcoTask.repository.TaskTypeRepository
import com.projects.EcoTask.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.Date

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository,
    private val taskTypeRepository: TaskTypeRepository
) {

    fun createTask(userId: Long, request: CreateTaskRequest): Tasks {

        val user = userRepository.findById(userId)
            .orElseThrow { RuntimeException("User not found") }

        val taskType  =
            try {
                taskTypeRepository.findById(request.taskTypeId).taskType
            } catch (e: Exception) {
                RuntimeException("TaskType not found : $e")
            }

        val task = Tasks(
            taskName = request.taskName,
            taskDesc = request.taskDesc,
            taskDueDate = request.dueDate,
            taskCreatedAt = System.currentTimeMillis(),
            user = user,
            taskType = taskType as String, // to fix incompatible values types
            isCompleted = false
        )

        return taskRepository.save(task)
    }

    fun getTasks(userId: Long) =
        taskRepository.findByUserId(userId)


    fun updateTask(taskId: Long, request: UpdateTaskRequest): Tasks {

        val task = taskRepository.findById(taskId)
            .orElseThrow { RuntimeException("Task not found") }

        val updated = task.copy(
            taskName = request.taskName ?: task.taskName,
            taskDesc = request.taskDesc ?: task.taskDesc,
            taskDueDate = request.dueDate ?: task.taskDueDate
        )

        return taskRepository.save(updated)
    }


    fun markAsCompleted(taskId: Long): Tasks {
        val task = taskRepository.findById(taskId)
            .orElseThrow { RuntimeException("Task not found") }

        val updated = task.copy(isCompleted = true)

        return taskRepository.save(updated)
    }

    fun deleteTask(taskId: Long) {
        if (!taskRepository.existsById(taskId)) {
            throw RuntimeException("Task not found")
        }
        taskRepository.deleteById(taskId)
    }

}