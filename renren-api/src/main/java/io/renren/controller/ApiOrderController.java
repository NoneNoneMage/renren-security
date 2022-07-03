package io.renren.controller;

import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.entity.TokenEntity;
import io.renren.form.LoginForm;
import io.renren.service.OrderService;
import io.renren.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/7/2 <br>
 * @see io.renren.controller <br>
 * @since R9.0<br>
 */
@RestController
@RequestMapping("/order")
@Api(tags="登录接口")
public class ApiOrderController {

    @Autowired
    TokenService tokenService;

    @Autowired
    OrderService orderService;

    @PostMapping("/list")
    @ApiOperation("訂單列表")
    public R login(@RequestHeader("token") String token){
        //表单校验
        TokenEntity tokenEntity = tokenService.queryByToken(token);

        if (null == tokenEntity) {
            return R.ok(new HashMap<>());
        }


        return R.ok(new HashMap<>());
    }
}
