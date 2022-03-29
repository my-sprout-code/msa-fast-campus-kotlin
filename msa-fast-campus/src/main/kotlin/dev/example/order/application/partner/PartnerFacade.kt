package dev.example.order.application.partner

import dev.example.order.domain.notification.NotificationService
import dev.example.order.domain.partner.PartnerCommand
import dev.example.order.domain.partner.PartnerInfo
import dev.example.order.domain.partner.PartnerService
import org.springframework.stereotype.Service

@Service
class PartnerFacade(
        private val partnerService: PartnerService,
        private val notificationService: NotificationService,
) {

    fun registerPartner(command: PartnerCommand): PartnerInfo {
        val partnerInfo = partnerService.registerPartner(command)
        notificationService.sendEmail(partnerInfo.email, "title", "description")
        return partnerInfo
    }


}