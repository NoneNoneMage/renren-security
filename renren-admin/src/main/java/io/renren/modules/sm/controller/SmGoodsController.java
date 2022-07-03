package io.renren.modules.sm.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.sm.entity.SmGoodsEntity;
import io.renren.common.sm.service.SmGoodsService;
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

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/5/4 <br>
 * @see io.renren.modules.sm.controller <br>
 * @since R9.0<br>
 */
@RestController
@RequestMapping("sm/goods")
public class SmGoodsController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(SmGoodsController.class);

    @Autowired
    private SmGoodsService smGoodsService;

    @SysLog("查询菜品")
    @RequestMapping("list")
    @RequiresPermissions("sm:restaurant:manageGoods")
    public R listFolderGoods (@RequestParam Map<String, Object> params) {
        PageUtils page = smGoodsService.queryPage(params);
        return R.ok().put("page", page);
    }

    @SysLog("保存菜品")
    @RequestMapping("/save")
    @RequiresPermissions("sm:restaurant:manageGoods")
    public R save(@RequestBody SmGoodsEntity smGoodsEntity){
        ValidatorUtils.validateEntity(smGoodsEntity, AddGroup.class);
        smGoodsEntity.setCreateTime(new Date());
        smGoodsEntity.setCreateUserId(getUserId());
        smGoodsService.save(smGoodsEntity);
        return R.ok();
    }

    @SysLog("更新菜品")
    @RequestMapping("/update")
    @RequiresPermissions("sm:restaurant:manageGoods")
    public R update(@RequestBody SmGoodsEntity smGoodsEntity){
        ValidatorUtils.validateEntity(smGoodsEntity, AddGroup.class);
        smGoodsEntity.setUpdateTime(new Date());
        smGoodsEntity.setUpdateUserId(getUserId());
        smGoodsService.saveOrUpdate(smGoodsEntity);
        return R.ok();
    }

    /**
     * 菜品信息
     */
    @RequestMapping("/info/{goodsId}")
    @RequiresPermissions("sm:restaurant:manageGoods")
    public R info(@PathVariable("goodsId") Long goodsId){
        SmGoodsEntity goodsEntity = smGoodsService.getById(goodsId);
        return R.ok().put("goods", goodsEntity);
    }

    /**
     * 删除菜品
     */
    @SysLog("删除菜品")
    @RequestMapping("/delete/{goodsId}")
    @RequiresPermissions("sm:restaurant:manageGoods")
    public R delete(@PathVariable("goodsId") Long goodsId){
        smGoodsService.removeById(goodsId);
        return R.ok();
    }

    @SysLog("批量删除菜品")
    @RequestMapping("/delete")
    @RequiresPermissions("sm:restaurant:manageGoods")
    public R delete(@RequestBody Long[] goodsIds){
        smGoodsService.removeByIds(Arrays.asList(goodsIds));
        return R.ok();
    }
}
