package dev.example.order.common.response;

class CommonResponse<T>(
        private val result: Result? = null,
        private val data: T? = null,
        private val message: String? = null,
        private val errorCode: String? = null,
) {

    enum class Result {
        SUCCESS, FAIL
    }

    companion object {
        fun <T> success(data: T): CommonResponse<T> {
            return success(data, null)
        }

        fun <T> success(data: T, message: String?): CommonResponse<T> {
            return CommonResponse(
                    result = Result.SUCCESS,
                    data = data,
                    message = message
            )
        }

        fun fail(message: String?, errorCode: String?): CommonResponse<Any> {
            return CommonResponse(
                    result = Result.FAIL,
                    message = message,
                    errorCode = errorCode
            )
        }

        fun fail(errorCode: ErrorCode): CommonResponse<Any> {
            return CommonResponse(
                    result = Result.FAIL,
                    message = errorCode.getErrorMsg(),
                    errorCode = errorCode.name
            )
        }
    }
}
