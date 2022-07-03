package io.renren.common.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.sm.dao.SmGoodsDao;
import io.renren.common.sm.entity.SmGoodsEntity;
import io.renren.common.sm.service.SmGoodsService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/5/4 <br>
 * @see io.renren.common.sm.service.impl <br>
 * @since R9.0<br>
 */
@Service
public class SmGoodsServiceImpl extends ServiceImpl<SmGoodsDao, SmGoodsEntity> implements SmGoodsService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = MapUtils.getString(params, "name");
        Long folderId = MapUtils.getLong(params, "folderId");
        IPage<SmGoodsEntity> page = this.page(
                new Query<SmGoodsEntity>().getPage(params),
                new QueryWrapper<SmGoodsEntity>().like(StringUtils.isNotBlank(name),"name", name).eq("folder_id", folderId)
        );

        return new PageUtils(page);
    }

    @Override
    public List<SmGoodsEntity> queryGoodsByFolderId(Long folderId) {
        List<SmGoodsEntity> smGoodsEntities = this.list(new QueryWrapper<SmGoodsEntity>().eq("folder_id", folderId));
        if (CollectionUtils.isNotEmpty(smGoodsEntities)) {
            for (SmGoodsEntity smGoodsEntity : smGoodsEntities) {
                //todo 查询订单表
                smGoodsEntity.setSellCount(0);
            }
        }
        return smGoodsEntities;
    }
}
