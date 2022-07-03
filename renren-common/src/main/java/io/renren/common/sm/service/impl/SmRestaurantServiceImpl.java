package io.renren.common.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.sm.dao.SmRestaurantDao;
import io.renren.common.sm.entity.SmRestaurantEntity;
import io.renren.common.sm.service.SmRestaurantService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/5/2 <br>
 * @see io.renren.common.sm.service.impl <br>
 * @since R9.0<br>
 */
@Service
public class SmRestaurantServiceImpl extends ServiceImpl<SmRestaurantDao, SmRestaurantEntity> implements SmRestaurantService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = MapUtils.getString(params, "name");
        IPage<SmRestaurantEntity> page = this.page(
                new Query<SmRestaurantEntity>().getPage(params),
                new QueryWrapper<SmRestaurantEntity>().like(StringUtils.isNotBlank(name),"name", name)
        );

        return new PageUtils(page);
    }
}
