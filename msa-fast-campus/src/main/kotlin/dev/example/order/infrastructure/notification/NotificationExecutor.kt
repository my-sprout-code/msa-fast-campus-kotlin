package dev.example.order.infrastructure.notification

import dev.example.order.domain.notification.NotificationService
import org.springframework.stereotype.Component

@Component
class NotificationExecutor : NotificationService {
    override fun sendEmail(email: String, title: String, description: String) = println("sendEmail")

    override fun sendKakao(phoneNo: String, description: String) = println("sendKakao")

    override fun sendSms(phoneNo: String, description: String) = println("sendSms")
}
