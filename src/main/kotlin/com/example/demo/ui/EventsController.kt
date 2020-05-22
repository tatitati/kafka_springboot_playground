package com.example.demo.ui

import com.example.demo.domain.LibraryEvent
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EventsController{

    @PostMapping("/v1/libraryevent")
    fun handler(@RequestBody libraryEvent: LibraryEvent): ResponseEntity<LibraryEvent>{
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(libraryEvent)
    }
}