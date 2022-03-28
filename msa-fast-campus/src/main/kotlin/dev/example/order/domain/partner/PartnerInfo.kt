package dev.example.order.domain.partner

data class PartnerInfo(
        val id: Long,
        val partnerToken: String,
        val partnerName: String,
        val businessNo: String,
        val email: String,
        val status: Partner.Status
) {
    companion object {
        fun create(partner: Partner): PartnerInfo =
                PartnerInfo(
                        partner.id ?: throw RuntimeException(),
                        partner.partnerToken,
                        partner.partnerName,
                        partner.businessNo,
                        partner.email,
                        partner.status,
                )
    }
}
