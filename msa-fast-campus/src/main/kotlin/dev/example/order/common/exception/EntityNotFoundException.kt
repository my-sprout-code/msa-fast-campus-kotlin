package dev.example.order.common.exception

import dev.example.order.common.response.ErrorCode

class EntityNotFoundException : BaseException {
    constructor() : super(ErrorCode.COMMON_INVALID_PARAMETER)
    constructor(message: String) : super(message, ErrorCode.COMMON_INVALID_PARAMETER)
}
