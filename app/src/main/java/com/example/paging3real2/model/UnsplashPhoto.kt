package com.example.paging3real2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id:String,
    val description:String,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
) : Parcelable {

    override fun hashCode(): Int {
        return super.hashCode()
    }

    @Parcelize
    data class UnsplashPhotoUrls(
        val raw : String,
        val full : String,
        val regular : String,
        val small : String,
        val thumb : String
    ) : Parcelable

    @Parcelize
    data class UnsplashUser(
        val name:String,
        val username:String
    ) : Parcelable {
        val attributionUrl
            get() = "https://unsplash.com/$username?utm_source=Paging3Real&utm_medium=referral"
    }
}

