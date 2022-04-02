package dev.example.order.common.response;

import com.google.common.collect.Lists
import dev.example.order.common.exception.BaseException
import dev.example.order.common.interceptor.CommonHttpRequestInterceptor
import org.apache.catalina.connector.ClientAbortException
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.core.NestedExceptionUtils
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class CommonControllerAdvice {
    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     * http status: 500 AND result: FAIL
     * 시스템 예외 상황. 집중 모니터링 대상
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = [Exception::class])
    fun onException(e: Exception): CommonResponse<*> {
        val eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.error("eventId = {} ", eventId, e);
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)
    }

    /**
     * http status: 200 AND result: FAIL
     * 시스템은 이슈 없고, 비즈니스 로직 처리에서 에러가 발생함
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = [BaseException::class])
    fun onBaseException(e: BaseException): CommonResponse<*> {
        val eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        if (SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST.contains(e.errorCode)) {
            log.error("[BaseException] eventId = {}, cause = {}, errorMsg = {}", eventId,
                    NestedExceptionUtils.getMostSpecificCause(e), NestedExceptionUtils.getMostSpecificCause(e).message);
        } else {
            log.warn("[BaseException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e), NestedExceptionUtils.getMostSpecificCause(e).message);
        }
        return CommonResponse.fail(e.message, e.errorCode.name)
    }

    /**
     * 예상치 않은 Exception 중에서 모니터링 skip 이 가능한 Exception 을 처리할 때
     * ex) ClientAbortException
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = [ClientAbortException::class])
    fun skipException(e: Exception): CommonResponse<*> {
        val eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.warn("[skipException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e), NestedExceptionUtils.getMostSpecificCause(e).message);
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)
    }

    /**
     * http status: 400 AND result: FAIL
     * request parameter 에러
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): CommonResponse<*> {
        val eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.warn("[BaseException] eventId = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e).message);
        val bindingResult = e.bindingResult
        val fe = bindingResult.fieldError
        return if (fe != null) {
            val message = "Request Error" + " " + fe.field + "=" + fe.rejectedValue + " (" + fe.defaultMessage + ")"
            CommonResponse.fail(message, ErrorCode.COMMON_INVALID_PARAMETER.name)
        } else {
            CommonResponse.fail(ErrorCode.COMMON_INVALID_PARAMETER.getErrorMsg(), ErrorCode.COMMON_INVALID_PARAMETER.name)
        }
    }

    companion object {
        private val SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST: List<ErrorCode> = Lists.newArrayList()
    }
}
