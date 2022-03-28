package dev.example.order.domain.partner

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PartnerServiceImpl(
        private val partnerStore: PartnerStore,
        private val partnerReader: PartnerReader
) : PartnerService {

    override fun registerPartner(command: PartnerCommand): PartnerInfo {
        val initPartner = command.toEntity()
        val partner = partnerStore.store(initPartner)
        return PartnerInfo.create(partner)
    }

    @Transactional(readOnly = true)
    override fun getPartnerInfo(partnerToken: String): PartnerInfo {
        val partner = partnerReader.getPartner(partnerToken)
        return PartnerInfo.create(partner)
    }

    override fun enablePartner(partnerToken: String): PartnerInfo {
        val partner: Partner = partnerReader.getPartner(partnerToken)
        partner.enable()
        return PartnerInfo.create(partner)
    }

    override fun disablePartner(partnerToken: String): PartnerInfo {
        val partner: Partner = partnerReader.getPartner(partnerToken)
        partner.disable()
        return PartnerInfo.create(partner)
    }
}