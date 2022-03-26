package com.example.candyspace_tech.model

import java.text.SimpleDateFormat
import java.util.*

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

    fun getCreationDate(): String{
        val convertedDate = Date(userData.creation_date*1000)

        val dateFormat = SimpleDateFormat("dd MMM yyyy")
        val simpleDate = dateFormat.format(convertedDate)
        return simpleDate
    }

    fun getUsersLocation(): String{
        if(userData.location != ""){
            return "Location - ${userData.location}"
        }
        return ""
    }
}