package com.example.candyspace_tech.model

object Session_User {
    var userData: User

    init {
        userData = User()
    }

    fun getBadgesText() : String{
        val badges = userData.badge_counts
        if (badges != null) {

                return "Badges - [${badges.bronze} Bronze, ${badges.silver} Silver, ${badges.gold} Gold]"
        }
        return  ""
    }
}