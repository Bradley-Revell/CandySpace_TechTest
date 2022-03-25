package com.example.candyspace_tech.model

data class UserResponse (
    val items: Array<User>?
)

data class User(
    val badge_counts: Badges,
    val account_id: Int,
    val is_employee: Boolean,
    val last_access_date: Int,
    val reputation_change_year: Int,
    val reputation_change_quarter: Int,
    val reputation_change_month: Int,
    val reputation_change_week: Int,
    val reputation_change_day: Int,
    val reputation: Int,
    val creation_date: Long,
    val user_type: String,
    val user_id: Int,
    val link: String,
    val profile_image: String,
    val display_name: String
)

data class Badges(
    val bronze: Int,
    val silver: Int,
    val gold: Int
)


data class TopTagResponse(
    val items: Array<TagDetails>?,
    val has_more: Boolean,
    val quota_max: Int,
    val quota_remianing: Int
)

data class TagDetails(
    val user_id: Int,
    val answer_count: Int,
    val answer_score: Int,
    val question_count: Int,
    val question_score: Int,
    val tag_name: String
)