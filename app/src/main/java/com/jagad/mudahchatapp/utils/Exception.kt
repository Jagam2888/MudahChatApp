package com.jagad.mudahchatapp.utils

import java.io.IOException

/**
 * Created by jagad on 8/10/2021
 */
class APIException(message : String) : IOException(message)
class NoInternetException(message: String) : IOException(message)