package io.renren.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.sm.entity.SmGoodsEntity;
import io.renren.common.sm.entity.SmGoodsFolderEntity;
import io.renren.common.sm.entity.SmRestaurantEntity;
import io.renren.common.sm.service.SmGoodsFolderService;
import io.renren.common.sm.service.SmGoodsService;
import io.renren.common.sm.service.SmRestaurantService;
import io.renren.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/5/6 <br>
 * @see io.renren.controller <br>
 * @since R9.0<br>
 */
@RestController
@RequestMapping("/")
@Api(tags="餐馆接口")
public class ApiRestaurantController {
    @Autowired
    SmRestaurantService smRestaurantService;

    @Autowired
    SmGoodsFolderService smGoodsFolderService;

    @Autowired
    SmGoodsService smGoodsService;

    private static final Logger logger = LoggerFactory.getLogger(ApiRestaurantController.class);

    @RequestMapping(value = "restaurantList", method = RequestMethod.GET)
    @ApiOperation("餐馆列表")
    public R restaurantList(@RequestParam Map params){
        logger.debug("restaurantList begin... params:[{}]", params);
        String name = MapUtils.getString(params, "name");
        List<SmRestaurantEntity> result = null;
        if (StringUtils.isNotEmpty(name)) {
           result = smRestaurantService.list(new QueryWrapper<SmRestaurantEntity>().like(StringUtils.isNotBlank(name), "name", name));
        }
        else {
            result = smRestaurantService.list();
        }

        if (CollectionUtils.isNotEmpty(result)) {
            for (SmRestaurantEntity smRestaurantEntity : result) {
                Integer monthSale = new Random().nextInt(4000);
                smRestaurantEntity.setMonthSaleNum(monthSale.longValue());
                smRestaurantEntity.setDistance(new Random().nextInt(10) + "km");
                smRestaurantEntity.setAverageDeliveryTime(new Random().nextInt(100));
            }
        }
        logger.debug("restaurantList end... restaurantList:[{}]", result);
        return R.ok().put("restaurantList", result);
    }

    @RequestMapping(value = "folders", method = RequestMethod.GET)
    @ApiOperation("餐馆列表")
    public R folders(@RequestParam Map params){
        logger.debug("folders begin... params:[{}]", params);
        Long restaurantId = MapUtils.getLong(params, "restaurantId");
        List<SmGoodsFolderEntity> goodsFolderEntities = queryFolders(restaurantId);
        logger.debug("folders end... folders:[{}]", goodsFolderEntities);
        return R.ok().put("folders", goodsFolderEntities);
    }

    @RequestMapping(value = "restaurant", method = RequestMethod.GET)
    @ApiOperation("餐馆")
    public R restaurant(@RequestParam Map params){
        logger.debug("restaurant begin... params:[{}]", params);
        Long restaurantId = MapUtils.getLong(params, "restaurantId");
        SmRestaurantEntity smRestaurantEntity = smRestaurantService.getById(restaurantId);
        smRestaurantEntity.setFolders(queryFolders(restaurantId));
        logger.debug("restaurant end... restaurant:[{}]", smRestaurantEntity);
        return R.ok().put("restaurant", smRestaurantEntity);
    }

    private List<SmGoodsFolderEntity> queryFolders(Long restaurantId) {
        List<SmGoodsFolderEntity> goodsFolderEntities = smGoodsFolderService.queryAllFolderByRestaurantId(restaurantId);
        if (CollectionUtils.isNotEmpty(goodsFolderEntities)) {
            Iterator it = goodsFolderEntities.iterator();
            while (it.hasNext()) {
                SmGoodsFolderEntity smGoodsFolderEntity = (SmGoodsFolderEntity) it.next();
                smGoodsFolderEntity.setType(-1);
                List<SmGoodsEntity> goodsEntities = smGoodsService.queryGoodsByFolderId(smGoodsFolderEntity.getId());
                if (CollectionUtils.isEmpty(goodsEntities)) {
                    it.remove();
                }
                else {
                    smGoodsFolderEntity.setFoods(goodsEntities);
                }
            }
        }
        return goodsFolderEntities;
    }
}
