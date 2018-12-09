package com.pavelperc.tutors.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFoundException(message: String) : RuntimeException(message)


@ResponseStatus(value = HttpStatus.CONFLICT)
class ConflictException(message: String) : RuntimeException(message)


