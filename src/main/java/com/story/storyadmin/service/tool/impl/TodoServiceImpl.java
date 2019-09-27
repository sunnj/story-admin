package com.story.storyadmin.service.tool.impl;

import com.story.storyadmin.domain.entity.tool.Todo;
import com.story.storyadmin.mapper.tool.TodoMapper;
import com.story.storyadmin.service.tool.TodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 待办事项 服务实现类
 * </p>
 *
 * @author sunningjun
 * @since 2019-08-14
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements TodoService {

}
