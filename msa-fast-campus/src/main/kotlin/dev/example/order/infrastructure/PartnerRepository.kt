package dev.example.order.infrastructure

import dev.example.order.domain.partner.Partner
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PartnerRepository : JpaRepository<Partner, Long> {
    fun findByPartnerToken(partnerToken: String): Optional<Partner>
}
