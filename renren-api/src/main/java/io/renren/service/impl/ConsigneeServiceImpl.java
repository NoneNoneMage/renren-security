package io.renren.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.dao.ConsigneeDao;
import io.renren.entity.ConsigneeEntity;
import io.renren.service.ConsigneeService;
import org.springframework.stereotype.Service;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/7/2 <br>
 * @see io.renren.service.impl <br>
 * @since R9.0<br>
 */
@Service("consigneeService")
public class ConsigneeServiceImpl extends ServiceImpl<ConsigneeDao, ConsigneeEntity> implements ConsigneeService {
}
