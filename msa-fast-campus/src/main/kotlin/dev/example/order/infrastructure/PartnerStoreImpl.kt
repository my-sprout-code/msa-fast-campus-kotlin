package dev.example.order.infrastructure

import dev.example.order.domain.partner.Partner
import dev.example.order.domain.partner.PartnerStore
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component
import java.util.*

@Component
class PartnerStoreImpl(
        private val partnerRepository: PartnerRepository
) : PartnerStore {
    override fun store(partner: Partner): Partner {
        if (StringUtils.isEmpty(partner.partnerToken)) {
            throw RuntimeException()
        }
        if (StringUtils.isEmpty(partner.partnerName)) {
            throw RuntimeException()
        }
        if (StringUtils.isEmpty(partner.businessNo)) {
            throw RuntimeException()
        }
        if (StringUtils.isEmpty(partner.email)) {
            throw RuntimeException()
        }
        if (Objects.isNull(partner.status)) {
            throw RuntimeException()
        }
        return partnerRepository.save(partner)
    }
}