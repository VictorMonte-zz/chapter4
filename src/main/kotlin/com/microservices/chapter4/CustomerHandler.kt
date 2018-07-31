package com.microservices.chapter4

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.ServerResponse.status

@Component
class CustomerHandler(val customerService: CustomerService){
    fun get(serverRequest: ServerRequest) =
            ok().body(customerService.getCustomer(serverRequest.pathVariable("id").toInt()), Customer::class.java)
                    .flatMap { ok().body(fromObject(it)) }
                    .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun search(serverRequest: ServerRequest) =
            ok().body(customerService.searchCustomers(serverRequest.queryParam("nameFilter")
                    .orElse("")), Customer::class.java)
}