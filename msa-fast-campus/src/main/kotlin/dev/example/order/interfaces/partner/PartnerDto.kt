package dev.example.order.interfaces.partner

import dev.example.order.domain.partner.Partner
import dev.example.order.domain.partner.PartnerCommand
import dev.example.order.domain.partner.PartnerInfo

class PartnerDto {
    data class RegisterRequest(
            val partnerName: String,
            val businessNo: String,
            val email: String,
    ) {
        fun toCommand(): PartnerCommand = PartnerCommand(partnerName, businessNo, email)
    }

    data class RegisterResponse(
            val id: Long,
            val partnerToken: String,
            val partnerName: String,
            val businessNo: String,
            val email: String,
            val status: Partner.Status
    ) {
        companion object {
            fun create(partnerInfo: PartnerInfo): RegisterResponse = RegisterResponse(
                    partnerInfo.id,
                    partnerInfo.partnerToken,
                    partnerInfo.partnerName,
                    partnerInfo.businessNo,
                    partnerInfo.email,
                    partnerInfo.status
            )
        }
    }
}
