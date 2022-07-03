package io.renren.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
        ElementType.METHOD, ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 表示用户不登录就能调用的服务
 *
 */
public @interface IgnoreSession {
}