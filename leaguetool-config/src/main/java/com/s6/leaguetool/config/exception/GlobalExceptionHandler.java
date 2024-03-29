package com.s6.leaguetool.config.exception;

import com.s6.leaguetool.enums.HttpResultEnum;
import lombok.extern.slf4j.Slf4j;
import com.s6.leaguetool.model.R;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 *
 * @description: 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 自定义异常
     * @param ex 自定义异常
     * @return R
     */
    @ExceptionHandler({GlobalException.class})
    @ResponseBody
    public R<GlobalException> globalException(GlobalException ex) {
        return resultFormatNoData(ex.getCode(), ex.getMsg(), ex);
    }


    /**
     * 运行时异常
     * @param ex 运行时异常
     * @return R
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public R<RuntimeException> runtimeExceptionHandler(RuntimeException ex) {
        return resultFormat(HttpResultEnum.RUN_EXCEPTION.getCode(), "运行时异常", ex);
    }

    /**
     * 空指针异常
     * @param ex 空指针异常
     * @return R
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public R<NullPointerException> nullPointerExceptionHandler(NullPointerException ex) {
        return resultFormat(HttpResultEnum.ERR_500_EXCEPTION.getCode(), ex.getMessage(), ex);
    }

    /**
     * 类型转换异常
     * @param ex 类型转换异常
     * @return R
     */
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public R<ClassCastException> classCastExceptionHandler(ClassCastException ex) {
        return resultFormat(HttpResultEnum.CONVERT_EXCEPTION.getCode(), "类型转换异常", ex);
    }

    /**
     * IO异常
     * @param ex IO异常
     * @return R
     */
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public R<IOException> iOExceptionHandler(IOException ex) {
        return resultFormat(HttpResultEnum.IO_EXCEPTION.getCode(), "IO异常", ex);
    }

    /**
     * 未知方法异常
     * @param ex 未知方法异常
     * @return R
     */
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public R<NoSuchMethodException> noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return resultFormat(HttpResultEnum.UNKNOEWN_EXCEPTION.getCode(), "未知方法异常", ex);
    }

    /**
     * 数组越界异常
     * @param ex 数组越界异常
     * @return R
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public R<IndexOutOfBoundsException> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return resultFormat(HttpResultEnum.OVERSTEP_EXCEPTION.getCode(), "数组越界异常", ex);
    }

    /**
     * 400错误
     * @param ex 400错误
     * @return R
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public R<HttpMessageNotReadableException> requestNotReadable(HttpMessageNotReadableException ex) {
        return resultFormat(HttpResultEnum.ERR_400_EXCEPTION.getCode(), "400错误", ex);
    }

    /**
     * 400错误
     * @param ex 400错误
     * @return R
     */
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public R<TypeMismatchException> requestTypeMismatch(TypeMismatchException ex) {
        return resultFormat(HttpResultEnum.ERR_401_EXCEPTION.getCode(), "400错误", ex);
    }

    /**
     * 400错误
     * @param ex 400错误
     * @return R
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public R<MissingServletRequestParameterException> requestMissingServletRequest(MissingServletRequestParameterException ex) {
        String sb = "缺少参数: " + ex.getParameterName() +
                "类型: " +
                ex.getParameterType();
//        return resultFormat(HttpResultEnum.ERR_402_EXCEPTION.getCode(),sb.toString(), ex);
        return R.fail(HttpResultEnum.ERR_402_EXCEPTION.getCode(), sb, null);
    }

    /**
     * 405错误
     * @param ex 405错误
     * @return R
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public R<HttpRequestMethodNotSupportedException> request405(HttpRequestMethodNotSupportedException ex) {
        return resultFormat(HttpResultEnum.ERR_405_EXCEPTION.getCode(), "405错误,请使用标准的请求方式",ex);
    }

    /**
     * 406错误
     * @param ex 406错误
     * @return R
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public R<HttpMediaTypeNotAcceptableException> request406(HttpMediaTypeNotAcceptableException ex) {
        return resultFormat(HttpResultEnum.ERR_406_EXCEPTION.getCode(), "406错误", ex);
    }

    /**
     * 500错误
     * @param ex 500错误
     * @return R
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseBody
    public R<RuntimeException> server500(RuntimeException ex) {
        return resultFormat(HttpResultEnum.ERR_500_EXCEPTION.getCode(), "500错误", ex);
    }

//    /**
//     * 未授权异常，shiro未授权，就是判断权限，权限不足的时候
//     *
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler({UnauthorizedException.class,})
//    @ResponseBody
//    public R server_shiro_unPermission(UnauthorizedException ex) {
//        return resultFormat(HttpResultEnum.POWER_ERR.getCode(), HttpResultEnum.POWER_ERR.getMessage(), ex);
//    }


    /**
     * 栈溢出
     * @param ex 栈溢出
     * @return R
     */
    @ExceptionHandler({StackOverflowError.class})
    @ResponseBody
    public R<StackOverflowError> requestStackOverflow(StackOverflowError ex) {
        return resultFormat(HttpResultEnum.STACK_OVERFLOW_EXCEPTION.getCode(), "栈溢出", ex);
    }

    /**
     * 除数不能为0
     * @param ex 除数不能为0
     * @return R
     */
    @ExceptionHandler({ArithmeticException.class})
    @ResponseBody
    public R<ArithmeticException> arithmeticException(ArithmeticException ex) {
        return resultFormat(HttpResultEnum.DIVISOR_ZERO_EXCEPTION.getCode(), "除数不能为0", ex);
    }

    /**
     * 断言异常
     * @param ex 断言异常
     * @return R
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public R<IllegalArgumentException> illegalArgumentException(IllegalArgumentException ex) {
        return resultFormat(HttpResultEnum.ERR_500_EXCEPTION.getCode(), ex.getMessage(), ex);
    }

    /**
     * 其他错误
     * @param ex 其他错误
     * @return R
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public R<Exception> exception(Exception ex) {
        return resultFormat(15, "未知错误", ex);
    }

//    /**
//     * 参数校验异常(Hibernate的参数校验注解)
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler({BindException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})
//    @ResponseBody
//    public R handleMethodArgumentNotValidException(Exception e) {
//        // 错误信息
////        StringBuilder sb = new StringBuilder("参数校验失败：");
//        StringBuilder sb = new StringBuilder();
//
//        // 错误信息map
//        Map<String, String> error = new HashMap<>();
//
//        String msg = "";
//        if (!(e instanceof BindException) && !(e instanceof MethodArgumentNotValidException)) {
//            for (ConstraintViolation cv : ((ConstraintViolationException) e).getConstraintViolations()) {
//                msg = cv.getMessage();
//                sb.append(msg).append("||");
//
//                Iterator<Path.Node> it = cv.getPropertyPath().iterator();
//                Path.Node last = null;
//                while (it.hasNext()) {
//                    last = (Path.Node) it.next();
//                }
//                /*for(last = null; it.hasNext(); last = (Path.Node)it.next()) {
//                }*/
//                error.put(last != null ? last.getName() : "", msg);
//            }
//        } else {
//            List<ObjectError> allErrors = null;
//            if (e instanceof BindException) {
//                allErrors = ((BindException) e).getAllErrors();
//            } else {
//                allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
//            }
//            // 拼接错误信息
//            for (ObjectError oe : allErrors) {
//                msg = oe.getDefaultMessage();
//                sb.append(msg).append("||");
//                if (oe instanceof FieldError) {
//                    error.put(((FieldError) oe).getField(), msg);
//                } else {
//                    error.put(oe.getObjectName(), msg);
//                }
//            }
//        }
////        return resultFormatNoData(HttpResultEnum.ERR_500_EXCEPTION.getCode(), sb.toString().substring(0, sb.length() - 2), e);
//        return HttpResult.err( sb.toString().substring(0, sb.length() - 2));
//    }


    private <T extends Throwable> R<T> resultFormat(HttpResultEnum httpResultEnum, T ex) {
        return resultFormat(httpResultEnum.getCode(),httpResultEnum.getMessage(),ex);
    }

    private <T extends Throwable> R<T> resultFormat(Integer code, String msg, T ex) {
        R<T> fail = R.fail(code, msg, ex);
        log.warn(msg, ex.getMessage(), ex);
        return fail;
    }

    private <T extends Throwable> R<T> resultFormatNoData(Integer code, String msg, T ex) {
        R<T> fail = R.fail(code, msg, null);
        log.warn(msg, ex.getMessage(), ex);
        return fail;
    }
}