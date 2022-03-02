package org.micro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.micro.mappers.YxUserMapper;
import org.micro.pojo.YxUserDO;
import org.micro.service.YxUserService;
import org.springframework.stereotype.Service;

/**
 * @author micro-paul
 * @date 2021年12月22日 14:02
 */
@Service
public class YxUserServiceImpl extends ServiceImpl<YxUserMapper, YxUserDO> implements YxUserService {

}
