package io.renren.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.entity.OrderEntity;
import io.renren.entity.OrderResultEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/7/2 <br>
 * @see io.renren.dao <br>
 * @since R9.0<br>
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
    List<OrderResultEntity> queryRecentOrderByUSerId(Long userId, Long limit);
}
