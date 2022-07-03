package io.renren.common.sm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.sm.entity.SmGoodsEntity;

import java.util.List;
import java.util.Map;

public interface SmGoodsService extends IService<SmGoodsEntity> {
    PageUtils queryPage(Map<String, Object> params);

    List<SmGoodsEntity> queryGoodsByFolderId(Long folderId);
}
