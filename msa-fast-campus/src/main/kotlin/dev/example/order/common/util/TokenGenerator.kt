package dev.example.order.common.util

import org.apache.commons.lang3.RandomStringUtils

object TokenGenerator {
    private const val TOKEN_LENGTH: Int = 20

    fun randomCharacter(length: Int): String = RandomStringUtils.randomAlphanumeric(length)

    fun randomCharacterWithPrefix(prefix: String): String = prefix + randomCharacter(TOKEN_LENGTH - prefix.length)
}
