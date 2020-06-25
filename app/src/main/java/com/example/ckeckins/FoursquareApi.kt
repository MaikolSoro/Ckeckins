package com.example.ckeckins

import android.location.Location
import java.util.*
import kotlin.collections.ArrayList

class FoursquareApi {
    var meta: Meta? = null
    var response: FoursquareResponseVenue? = null
}

class Meta{
 var code: Int = 0
 var erroresDetail:String = ""
}

class  FoursquareResponseVenue{
    var venues: ArrayList<Venue> ? = null
}

class Venue{
    var id: String = ""
    var name: String = ""
    var location: Location? = null
    var categories: ArrayList<Category>? = null
    var stats: Stats? = null
}

class  Location{
    var lat: Double = 0.0
    var lng: Double = 0.0
    var state: String = ""
    var country: String= ""
}

class Category{
    var id: String = ""
    var name: String = ""
    var icon: Icon? = null
}


class Icon{
 var prefix: String = ""
 var suffix: String = ""
}

class Stats{
    var checkinsCount = 0
    var usersCount = 0
    var tipoCount = 0
}