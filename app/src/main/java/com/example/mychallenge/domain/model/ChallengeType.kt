package com.example.mychallenge.domain.model

sealed class ChallengeType(val name: String, val url: String) {
    object Sports: ChallengeType(name = "Sports", url = "https://exercise.trekeducation.org/wp-content/uploads/sites/5/2018/01/woman-running.jpg")
    object Religion: ChallengeType(name = "Religion", url = "https://i.swncdn.com/media/800w/cms/CW/faith/66864-cross-sunset-gettyimages-chaiyapruek2520.1200w.tn.jpg")
    object Society: ChallengeType(name = "Society", url = "https://scx2.b-cdn.net/gfx/news/hires/2017/society.jpg")
    object SelfGrowth: ChallengeType(name = "Self growth", url = "https://assets.keap.com/image/upload/c_scale,w_600/v1539263284/How%20and%20Why%20to%20Encourage%20Personal%20Growth%20Within%20Your%20Business/GettyImages-687209536.jpg")
    object Addiction: ChallengeType(name = "Addiction", url = "https://banner2.cleanpng.com/20180528/tbu/kisspng-word-tag-cloud-drug-addict-5b0bdb22dc9fd5.0208038515275036509037.jpg")
    object Other: ChallengeType(name = "Other", url = "https://images.pond5.com/business-challenge-vector-concept-businesswoman-illustration-090039014_iconl_nowm.jpeg")

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

        fun toNameString(challengeType: ChallengeType): String {
            return challengeType.name
        }

        val challengeTypeList = listOf(
            Sports,
            Religion,
            Society,
            SelfGrowth,
            Addiction,
            Other
        )
    }
}
