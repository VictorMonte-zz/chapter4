package com.microservices.chapter4

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.toMono

@Component
class CustomerHandler{
    fun get(serverRequest: ServerRequest) =
            ok().body(Customer(1, "functional program").toMono(),
                    Customer::class.java)
}