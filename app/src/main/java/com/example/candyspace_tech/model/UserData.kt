package com.example.candyspace_tech.model

data class UserResponse (
    val items: Array<User>?
)

data class User(
    val badge_counts: Badges? = null,
    val account_id: Int = 0,
    val is_employee: Boolean = false,
    val last_access_date: Int = 0,
    val reputation_change_year: Int = 0,
    val reputation_change_quarter: Int = 0,
    val reputation_change_month: Int = 0,
    val reputation_change_week: Int = 0,
    val reputation_change_day: Int = 0,
    val reputation: Int = 0,
    val creation_date: Long = 0,
    val user_type: String = "",
    val user_id: Int = 0,
    val location: String = "",
    val link: String = "",
    val profile_image: String = "",
    val display_name: String = ""
)

data class Badges(
    val bronze: Int = 0,
    val silver: Int = 0,
    val gold: Int = 0
)


data class TopTagResponse(
    val items: Array<TagDetails>?,
    val has_more: Boolean,
    val quota_max: Int,
    val quota_remaining: Int
)

data class TagDetails(
    val user_id: Int,
    val answer_count: Int,
    val answer_score: Int,
    val question_count: Int,
    val question_score: Int,
    val tag_name: String
)