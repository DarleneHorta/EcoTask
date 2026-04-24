package com.projects.EcoTask.repository

import com.projects.EcoTask.model.TaskType

interface TaskTypeRepository {

    fun findById(taskTypeId: Long) : TaskType

}
