package com.example.demo.producer

import com.example.demo.domain.LibraryEvent
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFutureCallback

@Component
class LibraryEventProducer {

    //    producer:
    //      bootstrap-servers: localhost:9092
    //      key-serializer: org.apache.kafka.common.serialization.IntegerSerializer  // this is the int of kafkatemplate
    //      value-serializer: org.apache.kafka.common.serialization.StringSerializer // this is the String of Kafkatemplate
    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<Int, String>

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Throws(JsonProcessingException::class)
    fun sendLibraryEvent(libraryEvent: LibraryEvent) {
        val key = libraryEvent.libraryEventId
        val value = objectMapper!!.writeValueAsString(libraryEvent)
        val listenableFuture = kafkaTemplate.sendDefault(key, value)
        listenableFuture.addCallback({
            println(it!!.recordMetadata.toString())
        }, {
            println(it.toString())
        })
    }
}
