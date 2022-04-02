package dev.example.order.common.exception;

import dev.example.order.common.response.ErrorCode

class InvalidParamException : BaseException {
    constructor() : super(ErrorCode.COMMON_INVALID_PARAMETER)
    constructor(errorCode: ErrorCode) : super(errorCode)
    constructor(errorMsg: String) : super(errorMsg, ErrorCode.COMMON_INVALID_PARAMETER)
    constructor(message: String, errorCode: ErrorCode) : super(message, errorCode)
}
