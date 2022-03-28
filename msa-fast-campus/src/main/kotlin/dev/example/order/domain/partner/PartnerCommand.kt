package dev.example.order.domain.partner

data class PartnerCommand(
        val partnerName: String,
        val businessNo: String,
        val email: String
) {
    fun toEntity(): Partner = Partner(partnerName, businessNo, email)
}
