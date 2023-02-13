package com.example.cocktailapp.utils

class NullResponse(message: String = " Response was null") : Exception(message)
class FailResponse(message: String?) : Exception(message)