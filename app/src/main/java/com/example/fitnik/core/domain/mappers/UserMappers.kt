package com.example.fitnik.core.domain.mappers

import com.example.fitnik.core.data.local.entity.UserEntity
import com.example.fitnik.core.domain.model.User
import com.example.fitnik.core.domain.model.Workout

fun User.toEntity(): UserEntity {
    return UserEntity(
        uid = uid,
        firstName = firstName,
        lastName = lastName,
        email = email,
        gender = gender,
        activityLvl = activityLvl,
        objective = objective,
        age = age,
        weight = weight,
        height = height,
        accIscomplete = accIscomplete
    )
}

fun UserEntity.toDomain(workouts: List<Workout> = emptyList()): User {
    return User(
        uid = uid,
        firstName = firstName,
        lastName = lastName,
        email = email,
        gender = gender,
        activityLvl = activityLvl,
        objective = objective,
        age = age,
        weight = weight,
        height = height,
        accIscomplete = accIscomplete,
        workouts = workouts // vacíos, los manejaremos desde otra tabla
    )
}