package com.example.fitnik.core.domain.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

object TrainingMockProvider {
    fun getMuscleGainTraining(): Workout {
        return Workout(
            id = UUID.randomUUID().toString(),
            name = "PUSH Hipertrofia",
            type = "Volumen",
            userId = "user_default",
            description = "Entrenamiento enfocado en ganar masa muscular en la parte superior",
            dayOfWeek = "Lunes",
            sessions = listOf(
                Session(
                    id = UUID.randomUUID().toString(),
                    number = 1,
                    name = "PUSH ENTRENO A",
                    sessionType = "Push",
                    feedback = "Buena sesión, bombeo en pectoral",
                    effortLevel = 8,
                    date = LocalDate.now().format(DateTimeFormatter.ISO_DATE),
                    exerciseGroups = listOf(
                        ExerciseGroup(
                            id = UUID.randomUUID().toString(),
                            name = "Calentamiento y Movilidad",
                            type = BlockType.NORMAL,
                            order = 1,
                            instructions = "Realizar sin descanso",
                            exercises = listOf(
                                Exercise(
                                    id = UUID.randomUUID().toString(),
                                    name = "Band Pull Apart",
                                    method = null,
                                    notes = "Movilidad cintura escapular",
                                    isWarmUp = true,
                                    restSeconds = 10,
                                    targetSets = 3,
                                    sets = List(3) { setNum ->
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = setNum + 1,
                                            reps = 15,
                                            isCompleted = true
                                        )
                                    }
                                ),
                                Exercise(
                                    id = UUID.randomUUID().toString(),
                                    name = "Y-Press con Banda",
                                    method = null,
                                    notes = "Movilidad cintura escapular",
                                    isWarmUp = true,
                                    restSeconds = 10,
                                    targetSets = 3,
                                    sets = List(3) { setNum ->
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = setNum + 1,
                                            reps = 15,
                                            isCompleted = true
                                        )
                                    }
                                ),
                            ),
                        ),
                        ExerciseGroup(
                            id = UUID.randomUUID().toString(),
                            name = "Trabajo de fuerza principal",
                            type = BlockType.NORMAL,
                            order = 2,
                            restBetweenSets = 90,
                            exercises = listOf(
                                Exercise(
                                    id = UUID.randomUUID().toString(),
                                    name = "Press superior multipower",
                                    method = "Series rectas",
                                    restSeconds = 90,
                                    targetSets = 4,
                                    rm = 100,
                                    sets = listOf(
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 1,
                                            weight = 80.0,
                                            reps = 4,
                                            completedReps = 4,
                                            percentage = 80,
                                            isCompleted = true
                                        ),
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 2,
                                            weight = 82.5,
                                            reps = 4,
                                            completedReps = 4,
                                            percentage = 82,
                                            isCompleted = true
                                        ),
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 3,
                                            weight = 85.0,
                                            reps = 4,
                                            completedReps = 4,
                                            percentage = 85,
                                            isCompleted = true
                                        ),
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 4,
                                            weight = 87.5,
                                            reps = 4,
                                            completedReps = 3,
                                            percentage = 87,
                                            isCompleted = true
                                        )
                                    ),
                                )
                            ),
                        ),
                        ExerciseGroup(
                            id = UUID.randomUUID().toString(),
                            name = "Trabajo J-Reps",
                            type = BlockType.NORMAL,
                            order = 3,
                            restBetweenSets = 60,
                            exercises = listOf(
                                Exercise(
                                    id = UUID.randomUUID().toString(),
                                    name = "Diamond Push Ups",
                                    method = "J-Reps",
                                    notes = "Parte difícil al fallo + parte fácil al fallo + 20\" movimiento completo",
                                    restSeconds = 60,
                                    targetSets = 3,
                                    sets = List(3) { setNum ->
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = setNum + 1,
                                            reps = null, // Al fallo
                                            isCompleted = setNum < 2 // Completó 2 de 3 series
                                        )
                                    }
                                ),
                            )
                        ),
                        ExerciseGroup(
                            id = UUID.randomUUID().toString(),
                            name = "Triset Hombros",
                            type = BlockType.TRISET,
                            order = 4,
                            restBetweenSets = 80,
                            exercises = listOf(
                                Exercise(
                                    id = UUID.randomUUID().toString(),
                                    name = "Elevaciones laterales",
                                    restSeconds = 0, // No hay descanso entre ejercicios del triset
                                    targetSets = 3,
                                    sets = listOf(
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 1,
                                            weight = 10.0,
                                            reps = 15,
                                            rir = 0,
                                            isCompleted = true
                                        ),
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 2,
                                            weight = 12.5,
                                            reps = 12,
                                            rir = 0,
                                            isCompleted = true
                                        ),
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 3,
                                            weight = 15.0,
                                            reps = 10,
                                            rir = 0,
                                            isCompleted = true
                                        )
                                    ),
                                ),
                                Exercise(
                                    id = UUID.randomUUID().toString(),
                                    name = "Press militar mancuernas",
                                    restSeconds = 0,
                                    targetSets = 3,
                                    sets = listOf(
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 1,
                                            weight = 12.5,
                                            reps = 15,
                                            rir = 0,
                                            isCompleted = true
                                        ),
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 2,
                                            weight = 15.0,
                                            reps = 12,
                                            rir = 0,
                                            isCompleted = true
                                        ),
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 3,
                                            weight = 17.5,
                                            reps = 10,
                                            rir = 0,
                                            isCompleted = true
                                        )
                                    )
                                ),
                                Exercise(
                                    id = UUID.randomUUID().toString(),
                                    name = "Elevaciones frontales",
                                    restSeconds = 80, // Descanso al final del triset
                                    targetSets = 3,
                                    sets = listOf(
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 1,
                                            weight = 7.5,
                                            reps = 15,
                                            rir = 0,
                                            isCompleted = true
                                        ),
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 2,
                                            weight = 10.0,
                                            reps = 12,
                                            rir = 0,
                                            isCompleted = true
                                        ),
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 3,
                                            weight = 12.5,
                                            reps = 10,
                                            rir = 0,
                                            isCompleted = true
                                        )
                                    ),
                                ),
                            ),
                        ),
                    ),
                ),
            ),
        )
    }

    fun getFatLossTraining(): Workout {
        return Workout(
            id = UUID.randomUUID().toString(),
            name = "PULL Definición",
            type = "Entreno B",
            userId = "user_default",
            description = "Entrenamiento para fortalecimiento y definición de espalda y bíceps",
            dayOfWeek = "Miércoles",
            sessions = listOf(
                Session(
                    id = UUID.randomUUID().toString(),
                    number = 1,
                    name = "PULL ENTRENO B",
                    sessionType = "Pull",
                    feedback = "Buena conexión en dorsales",
                    effortLevel = 7,
                    date = LocalDate.now().format(DateTimeFormatter.ISO_DATE),
                    exerciseGroups = listOf(
                        ExerciseGroup(
                            id = UUID.randomUUID().toString(),
                            name = "Calentamiento",
                            type = BlockType.WARMUP,
                            order = 1,
                            exercises = listOf(
                                Exercise(
                                    id = UUID.randomUUID().toString(),
                                    name = "Gemelos en Multipower",
                                    method = "Alta repetición",
                                    notes = "Trabajo de alta repetición",
                                    isWarmUp = true,
                                    targetSets = 1,
                                    sets = listOf(
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = 1,
                                            reps = 50,
                                            isCompleted = true,
                                            tempo = "2010" // Tiempo bajo tensión
                                        )
                                    )
                                ),
                                Exercise(
                                    id = UUID.randomUUID().toString(),
                                    name = "Pulldown",
                                    notes = "Movilidad escapular",
                                    isWarmUp = true,
                                    targetSets = 3,
                                    sets = List(3) { setNum ->
                                        Set(
                                            id = UUID.randomUUID().toString(),
                                            setNumber = setNum + 1,
                                            reps = 15,
                                            isCompleted = true
                                        )
                                    }
                                )
                            )
                        ),
                    ExerciseGroup(
                        id = UUID.randomUUID().toString(),
                        name = "Trabajo de fuerza bíceps",
                        type = BlockType.NORMAL,
                        order = 2,
                        restBetweenSets = 90,
                        exercises = listOf(
                            Exercise(
                                id = UUID.randomUUID().toString(),
                                name = "Curl barra Z",
                                method = "Series ascendentes",
                                restSeconds = 90,
                                targetSets = 4,
                                rm = 45,
                                sets = listOf(
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 1,
                                        weight = 36.0,
                                        reps = 4,
                                        percentage = 80,
                                        isCompleted = true
                                    ),
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 2,
                                        weight = 37.0,
                                        reps = 4,
                                        percentage = 82,
                                        isCompleted = true
                                    ),
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 3,
                                        weight = 38.0,
                                        reps = 4,
                                        percentage = 85,
                                        isCompleted = true
                                    ),
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 4,
                                        weight = 39.5,
                                        reps = 4,
                                        percentage = 87,
                                        isCompleted = true
                                    )
                                )
                            )
                        )
                    ),
                    ExerciseGroup(
                        id = UUID.randomUUID().toString(),
                        name = "Trabajo J-Reps",
                                        type = BlockType.NORMAL,
                        order = 3,
                        restBetweenSets = 60,
                        exercises = listOf(
                            Exercise(
                                id = UUID.randomUUID().toString(),
                                name = "Curl polea baja",
                                method = "J-Reps",
                                notes = "Parte difícil al fallo + parte fácil al fallo + 20\" movimiento completo",
                                restSeconds = 60,
                                targetSets = 3,
                                sets = List(3) { setNum ->
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = setNum + 1,
                                        isCompleted = true
                                    )
                                }
                            )
                        )
                    ),
                    ExerciseGroup(
                        id = UUID.randomUUID().toString(),
                        name = "Trabajo espalda",
                        type = BlockType.NORMAL,
                        order = 4,
                        restBetweenSets = 80,
                        exercises = listOf(
                            Exercise(
                                id = UUID.randomUUID().toString(),
                                name = "Dominadas supinas",
                                restSeconds = 80,
                                targetSets = 4,
                                sets = listOf(
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 1,
                                        reps = 15,
                                        rir = 0,
                                        isCompleted = true
                                    ),
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 2,
                                        reps = 12,
                                        rir = 0,
                                        isCompleted = true
                                    ),
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 3,
                                        reps = 10,
                                        rir = 0,
                                        isCompleted = true
                                    ),
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 4,
                                        reps = 8,
                                        rir = 0,
                                        isCompleted = true
                                    )
                                )
                            )
                        )
                    ),
                    ExerciseGroup(
                        id = UUID.randomUUID().toString(),
                        name = "Superset espalda",
                        type = BlockType.SUPERSET,
                        order = 5,
                        restBetweenSets = 80,
                        exercises = listOf(
                            Exercise(
                                id = UUID.randomUUID().toString(),
                                name = "Remo unilateral polea alta",
                                restSeconds = 0,
                                targetSets = 3,
                                sets = listOf(
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 1,
                                        weight = 30.0,
                                        reps = 15,
                                        rir = 0,
                                        isCompleted = true
                                    ),
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 2,
                                        weight = 35.0,
                                        reps = 12,
                                        rir = 0,
                                        isCompleted = true
                                    ),
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 3,
                                        weight = 40.0,
                                        reps = 10,
                                        rir = 0,
                                        isCompleted = true
                                    )
                                                )
                            ),
                            Exercise(
                                id = UUID.randomUUID().toString(),
                                name = "Remo con mancuernas",
                                restSeconds = 80,
                                targetSets = 3,
                                sets = listOf(
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 1,
                                        weight = 20.0,
                                        reps = 15,
                                        rir = 0,
                                        isCompleted = true
                                    ),
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 2,
                                        weight = 22.5,
                                        reps = 12,
                                        rir = 0,
                                        isCompleted = true
                                    ),
                                    Set(
                                        id = UUID.randomUUID().toString(),
                                        setNumber = 3,
                                        weight = 25.0,
                                        reps = 10,
                                        rir = 0,
                                        isCompleted = true
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )
        )
    }
}