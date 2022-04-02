package dev.example.order.common.exception

import dev.example.order.common.response.ErrorCode

/**
 * BaseException 또는 BaseException 을 확장한 Exception 은
 * 서비스 운영에서 예상이 가능한 Exception 을 표현한다.
 *
 * 그렇기 때문에 http status: 200 이면서 result: FAIL 을 표현하고
 * 특정 ErrorCode 만 alert 를 포함한 모니터링 대상으로 한다.
 */
open class BaseException : RuntimeException {
    val errorCode: ErrorCode

    constructor(errorCode: ErrorCode) : super(errorCode.getErrorMsg()) {
        this.errorCode = errorCode
    }

    constructor(message: String, errorCode: ErrorCode) : super(message) {
        this.errorCode = errorCode
    }

    constructor(message: String, errorCode: ErrorCode, cause: Throwable) : super(message, cause) {
        this.errorCode = errorCode
    }
}
