package com.example.flsdmessanger.data

class User(val uid: String, val username: String, val profileImage: String) {
    constructor() : this("", "", "")
}