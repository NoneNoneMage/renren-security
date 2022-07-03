package io.renren.common.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.sm.dao.SmGoodsFolderDao;
import io.renren.common.sm.entity.SmGoodsFolderEntity;
import io.renren.common.sm.service.SmGoodsFolderService;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class SmGoodsFolderServiceImpl extends ServiceImpl<SmGoodsFolderDao, SmGoodsFolderEntity> implements SmGoodsFolderService {
    @Override
    public List<SmGoodsFolderEntity> queryAllFolderByRestaurantId(Long restaurantId) {
        return this.list(new QueryWrapper<SmGoodsFolderEntity>().eq("restaurant_id", restaurantId).eq("status", 1));
    }
}
