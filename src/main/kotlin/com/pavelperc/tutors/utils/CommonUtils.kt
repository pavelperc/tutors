package com.pavelperc.tutors.utils

import java.util.*


fun <T> Optional<T>.getOrNull() : T? = this.orElse(null)