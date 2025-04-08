package com.example.fitnik.core.domain.model

object TrainingMockProvider {
    fun getMuscleGainTraining(): Training {
        return Training(
            name = "PUSH Hipertrofia",
            type = "Entreno A",
            sessions = listOf(
                Session(
                    number = 1,
                    exercises = listOf(
                        Exercise(
                            name = "Band Pull Apart",
                            sets = 3,
                            reps = "15",
                            notes = "Movilidad cintura escapular",
                            warmUp = true
                        ),
                        Exercise(
                            name = "Press superior multipower",
                            sets = 4,
                            reps = "4",
                            loadPercentage = "80%",
                            restSeconds = 90,
                            warmUp = false
                        ),
                        Exercise(
                            name = "Diamond Push Ups",
                            sets = 3,
                            reps = "J-Reps hasta el fallo",
                            method = "J-Reps",
                            restSeconds = 60,
                            warmUp = false
                        ),
                        Exercise(
                            name = "Triset Hombros",
                            sets = 3,
                            reps = "15-12-10",
                            method = "Triset",
                            restSeconds = 80,
                            notes = "Elevaciones laterales + Press militar + Elevaciones frontales",
                            rir = "0",
                            warmUp = false
                        )
                    )
                )
            )
        )
    }

    fun getWeightLossTraining(): Training {
        return Training(
            name = "FULLBODY Quemagrasa",
            type = "Entreno D",
            sessions = listOf(
                Session(
                    number = 1,
                    exercises = listOf(
                        Exercise(
                            name = "Circuito Core",
                            sets = 3,
                            reps = "20 reps por ejercicio",
                            warmUp = true,
                            notes = "Crunch invertido, crunch super slow, oblicuos y crunch en polea alta"
                        )
                    ) + listOf(
                        Exercise(
                            name = "Press militar multipower",
                            sets = 4,
                            reps = "8",
                            restSeconds = 60
                        ),
                        Exercise(
                            name = "Superset PullDown + Remo en punta",
                            sets = 3,
                            reps = "15",
                            method = "Superset",
                            restSeconds = 60,
                            rir = "0"
                        ),
                        Exercise(
                            name = "Press banca",
                            sets = 4,
                            reps = "10-12",
                            restSeconds = 60
                        )
                    )
                )
            )
        )
    }

    fun getFatLossTraining(): Training {
        return Training(
            name = "PULL Definición",
            type = "Entreno B",
            sessions = listOf(
                Session(
                    number = 1,
                    exercises = listOf(
                        Exercise(
                            name = "Pulldown + movilidad",
                            sets = 3,
                            reps = "15",
                            warmUp = true,
                            notes = "Trabajo de movilidad escapular"
                        )
                    ) + listOf(
                        Exercise(
                            name = "Curl barra Z",
                            sets = 4,
                            reps = "4",
                            loadPercentage = "80%",
                            restSeconds = 90
                        ),
                        Exercise(
                            name = "Dominadas supinas",
                            sets = 4,
                            reps = "15-12-10-8",
                            restSeconds = 80
                        ),
                        Exercise(
                            name = "Superset remo unil. polea + remo mancuerna",
                            sets = 3,
                            reps = "15-12-10",
                            method = "Superset",
                            restSeconds = 80,
                            rir = "0"
                        )
                    )
                )
            )
        )
    }

    fun getAllTrainings(): List<Training> = listOf(
        getMuscleGainTraining(),
        getWeightLossTraining(),
        getFatLossTraining()
    )
}