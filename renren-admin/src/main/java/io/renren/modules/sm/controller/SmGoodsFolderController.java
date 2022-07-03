package io.renren.modules.sm.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.sm.entity.SmGoodsFolderEntity;
import io.renren.common.sm.service.SmGoodsFolderService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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
@RequestMapping("sm/folder")
public class SmGoodsFolderController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(SmGoodsFolderController.class);

    @Autowired
    private SmGoodsFolderService smGoodsFolderService;

    @SysLog("查询菜品目录")
    @RequestMapping("/list/{restaurantId}")
    @RequiresPermissions("sm:restaurant:manageGoods")
    public R listFolders(@PathVariable("restaurantId") Long restaurantId){
        List<SmGoodsFolderEntity> goodsFolderEntities = smGoodsFolderService.queryAllFolderByRestaurantId(restaurantId);
        return R.ok().put("folders", goodsFolderEntities);
    }

    @SysLog("保存菜品目录")
    @RequestMapping("/save")
    @RequiresPermissions("sm:restaurant:manageGoods")
    public R save(@RequestBody SmGoodsFolderEntity goodsFolderEntity){
        ValidatorUtils.validateEntity(goodsFolderEntity, AddGroup.class);
        goodsFolderEntity.setCreateTime(new Date());
        goodsFolderEntity.setCreateUserId(getUserId());
        smGoodsFolderService.save(goodsFolderEntity);
        return R.ok();
    }

    @SysLog("更新菜品目录")
    @RequestMapping("/update")
    @RequiresPermissions("sm:restaurant:manageGoods")
    public R update(@RequestBody SmGoodsFolderEntity goodsFolderEntity){
        ValidatorUtils.validateEntity(goodsFolderEntity, AddGroup.class);
        goodsFolderEntity.setUpdateTime(new Date());
        goodsFolderEntity.setUpdateUserId(getUserId());
        smGoodsFolderService.saveOrUpdate(goodsFolderEntity);
        return R.ok();
    }

    /**
     * 目录信息
     */
    @RequestMapping("/info/{folderId}")
    @RequiresPermissions("sm:restaurant:manageGoods")
    public R info(@PathVariable("folderId") Long folderId){
        SmGoodsFolderEntity folderEntity = smGoodsFolderService.getById(folderId);
        return R.ok().put("folder", folderEntity);
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete/{folderId}")
    @RequiresPermissions("sm:restaurant:manageGoods")
    public R delete(@PathVariable("folderId") Long folderId){
        smGoodsFolderService.removeById(folderId);
        return R.ok();
    }
}
