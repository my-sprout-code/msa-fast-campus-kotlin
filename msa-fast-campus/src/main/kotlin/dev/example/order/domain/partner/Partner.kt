package dev.example.order.domain.partner

import dev.example.order.common.util.TokenGenerator
import dev.example.order.domain.AbstractEntity
import org.apache.commons.lang3.StringUtils
import javax.persistence.*


@Table(name = "partners")
@Entity
class Partner(
        val partnerName: String,
        val businessNo: String,
        val email: String,

        @Enumerated(EnumType.STRING)
        var status: Status = Status.ENABLE,

        val partnerToken: String = TokenGenerator.randomCharacterWithPrefix(PREFIX_PARTNER_ENTITY),

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        ) : AbstractEntity() {
    init {
        require(StringUtils.isNotBlank(partnerName))
        require(StringUtils.isNotBlank(businessNo))
        require(StringUtils.isNotBlank(email))
    }

    fun enable() {
        status = Status.ENABLE
    }

    fun disable() {
        status = Status.DISABLE
    }

    enum class Status(val description: String) {
        ENABLE("활성화"),
        DISABLE("비활성화")
    }

    companion object {
        private const val PREFIX_PARTNER_ENTITY = "ptn_"
    }
}
