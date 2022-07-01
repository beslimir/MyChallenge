package com.example.mychallenge.domain.model

sealed class ChallengeType(val name: String) {
    object Sports: ChallengeType("Sports")
    object Religion: ChallengeType("Religion")
    object Society: ChallengeType("Society")
    object SelfGrowth: ChallengeType("Self growth")
    object Addiction: ChallengeType("Addiction")
    object Other: ChallengeType("Other")

    companion object {
        fun fromString(name: String): ChallengeType {
            return when(name.lowercase()) {
                "Sports" -> Sports
                "Religion" -> Religion
                "Society" -> Society
                "Self growth" -> SelfGrowth
                "Addiction" -> Addiction
                else -> Other
            }
        }
    }
}
