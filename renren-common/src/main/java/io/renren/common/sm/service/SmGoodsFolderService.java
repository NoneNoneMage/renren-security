package io.renren.common.sm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.sm.entity.SmGoodsFolderEntity;

import java.util.List;

public interface SmGoodsFolderService extends IService<SmGoodsFolderEntity> {
    List<SmGoodsFolderEntity> queryAllFolderByRestaurantId(Long restaurantId);
}
