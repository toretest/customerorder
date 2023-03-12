package net.toregard.customerorder.restendpoint

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SeeMe {

    @GetMapping
    fun hello() = "ok"
}
