package com.devon.isearch.model.types

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Movie(@PrimaryKey var title: String) : RealmObject(){

    var movieId: String = ""

    constructor():this("")

    override fun equals(other: Any?): Boolean {
        return if(other is Movie){
            other.title == title
        }else{
            false
        }
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }
}
