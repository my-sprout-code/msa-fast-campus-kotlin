package dev.example.order.domain.partner

interface PartnerStore {
    fun store(initPartner: Partner): Partner
}