package io.renren.modules.sm.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.common.sm.entity.SmRestaurantEntity;
import io.renren.common.sm.service.SmRestaurantService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/5/2 <br>
 * @see io.renren.modules.sm.controller <br>
 * @since R9.0<br>
 */
@RestController
@RequestMapping("sm/restaurant")
public class SmRestaurantController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(SmRestaurantController.class);

    @Autowired
    private SmRestaurantService smRestaurantService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sm:restaurant:list")
    public R list(@RequestParam Map<String, Object> params){
        logger.debug("SmRestaurantController.list begin... params:[{}]", params);
        PageUtils page = smRestaurantService.queryPage(params);
        return R.ok().put("page", page);
    }

    @SysLog("保存门店")
    @RequestMapping("/save")
    @RequiresPermissions("sm:restaurant:save")
    public R save(@RequestBody SmRestaurantEntity restaurantEntity){
        ValidatorUtils.validateEntity(restaurantEntity, AddGroup.class);
        restaurantEntity.setCreateTime(new Date());
        restaurantEntity.setCreateUserId(getUserId());
        smRestaurantService.save(restaurantEntity);

        return R.ok();
    }

    @SysLog("修改门店")
    @RequestMapping("/update")
    @RequiresPermissions("sm:restaurant:update")
    public R update(@RequestBody SmRestaurantEntity restaurantEntity){
        ValidatorUtils.validateEntity(restaurantEntity, UpdateGroup.class);
        restaurantEntity.setUpdateTime(new Date());
        restaurantEntity.setUpdateUserId(getUserId());
        smRestaurantService.saveOrUpdate(restaurantEntity);

        return R.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{restaurantId}")
    @RequiresPermissions("sm:restaurant:info")
    public R info(@PathVariable("restaurantId") Long restaurantId){
        SmRestaurantEntity restaurantEntity = smRestaurantService.getById(restaurantId);
        return R.ok().put("restaurant", restaurantEntity);
    }
}
