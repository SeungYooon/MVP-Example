package com.example.rxjava.model

object DogList {
    fun getDoglistData(): List<Dog> {
        return listOf(
            Dog("말티즈", 3),
            Dog("리트리버", 5),
            Dog("웰시코기", 1)
        )
    }
}