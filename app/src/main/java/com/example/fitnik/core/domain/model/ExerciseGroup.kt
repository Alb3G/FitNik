package com.example.fitnik.core.domain.model

data class ExerciseGroup(
    val id: String = "",
    val name: String? = null,
    val type: BlockType = BlockType.NORMAL, // "NORMAL", "SUPERSET", "TRISET"
    val restBetweenSets: Int? = null,
    val order: Int = 0,
    val instructions: String? = null,
    val exercises: List<Exercise> = emptyList()
)

enum class BlockType {
    NORMAL, SUPERSET, TRISET, WARMUP
}