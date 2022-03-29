package dev.example.order.infrastructure.partner

import dev.example.order.domain.partner.Partner
import dev.example.order.domain.partner.PartnerReader
import org.springframework.stereotype.Component

@Component
class PartnerReadImpl(
        private val partnerRepository: PartnerRepository
) : PartnerReader {

    override fun getPartner(partnerToken: String): Partner =
            partnerRepository.findByPartnerToken(partnerToken).orElseThrow(::RuntimeException)
}
