package com.story.storyadmin.config.mongo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;

public abstract class StoryMongoTemplate<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public IPage<T> findPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> pageParam, T queryParam,String sortCol, String sortDir, Class<T> clazz) {
        long pageNo = pageParam.getCurrent();
        long pageSize = pageParam.getSize();

        Query query = new Query();

        Criteria criteria = buildCriteria(queryParam);
        query.addCriteria(criteria);

        Sort sort = this.buildJPABasicOrder(sortCol, sortDir);
        if (sort != null) {
            query.with(sort);
        }
        StoryMongoPageable pageable = new StoryMongoPageable();
        pageable.setPagenumber((int)pageNo);
        pageable.setPagesize((int)pageSize);
        if (sort != null) {
            pageable.setSort(sort);
        }
        Long count = mongoTemplate.count(query, clazz);
        List<T> list = mongoTemplate.find(query.with(pageable), clazz);
        Page<T> page = new PageImpl<>(list, pageable, count);
        IPage<T> pageResp = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        pageResp.setRecords(page.getContent());
        pageResp.setCurrent(page.getNumber() + 1);
        pageResp.setSize(page.getPageable().getPageSize());
        pageResp.setTotal(page.getTotalElements()/page.getPageable().getPageSize());
        pageResp.setTotal(Integer.valueOf(page.getTotalElements() + ""));
        return pageResp;
    }

    /**
     * 构造查询条件
     *
     * @param filter
     * @return
     */
    protected abstract Criteria buildCriteria(T filter);

    /**
     * 构造排序, 默认按照主键（id）倒序（desc）
     *
     * @param sortCol
     * @param sortDir
     * @return
     */
    private Sort buildJPABasicOrder(String sortCol, String sortDir) {
        if (StringUtils.isEmpty(sortCol)) {
            return new Sort(Sort.Direction.DESC, "id");
        }
        Sort sort = null;
        String[] sortColArr = sortCol.trim().split(",");
        String[] sortDirArr = sortDir.trim().split(",");
        int sortColLength = sortColArr.length;
        for (int i = 0; i < sortColLength; i++) {
            String col = sortColArr[i].trim();
            String dir = sortDirArr[i];
            if (i == 0) {
                sort = new Sort(buildDirection(dir), col);
                continue;
            }
            if (sort != null) {
                sort.and(new Sort(buildDirection(dir), col));
            }
        }
        return sort;
    }

    /**
     * 构造排序
     * @param sortDir
     * @return
     */
    private static Sort.Direction buildDirection(String sortDir) {
        Sort.Direction direction = Sort.Direction.ASC;
        switch (sortDir.trim().toLowerCase(Locale.ENGLISH)) {
            case "asc":
                direction = Sort.Direction.ASC;
                break;
            case "desc":
                direction = Sort.Direction.DESC;
                break;
            default:
                break;
        }
        return direction;
    }
}
