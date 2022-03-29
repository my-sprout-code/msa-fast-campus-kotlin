package dev.example.order.interfaces.partner

import dev.example.order.application.partner.PartnerFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/partners")
@RestController
class PartnerApiController (
        private val partnerFacade: PartnerFacade,
) {

    @PostMapping
    fun registerPartner(request: PartnerDto.RegisterRequest): ResponseEntity<PartnerDto.RegisterResponse> {
        val command = request.toCommand()
        val partnerInfo = partnerFacade.registerPartner(command)
        val registerResponse = PartnerDto.RegisterResponse.create(partnerInfo)
        return ResponseEntity.ok(registerResponse)
    }
}