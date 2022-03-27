package com.example.candyspace_tech.model

import java.text.SimpleDateFormat
import java.util.*

object Session_User {
    var userData: User

    init {
        userData = User()
    }

    //Generates the badges text that will be displayed
    fun getBadgesText() : String{
        val badges = userData.badge_counts
        if (badges != null) {

                return "Badges - [${badges.bronze} Bronze, ${badges.silver} Silver, ${badges.gold} Gold]"
        }
        return  ""
    }

    //Converts the epoch date to a readable string
    fun getCreationDate(): String {
        val convertedDate = Date(userData.creation_date * 1000)

        val dateFormat = SimpleDateFormat("dd MMM yyyy")
        return dateFormat.format(convertedDate)
    }

    //Returns the string that is required for the users location
    fun getUsersLocation(): String{
        if(userData.location != ""){
            return "Location - ${userData.location}"
        }
        return ""
    }
}