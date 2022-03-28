package dev.example.order.domain.partner

interface PartnerReader {
    fun getPartner(partnerToken: String): Partner
}
