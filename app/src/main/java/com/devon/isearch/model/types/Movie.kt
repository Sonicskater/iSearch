package com.devon.isearch.model.types

import io.realm.RealmObject

open class Movie(var name: String) : RealmObject(){
    constructor():this("")

    override fun equals(other: Any?): Boolean {
        return if(other is Movie){
            other.name == name
        }else{
            false
        }
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
