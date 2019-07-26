package com.story.storyadmin.domain.vo.sysmgr;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author sunningjun
 * @since 2018-12-28
 */
@Data
public class ResourceNode {
    private Long id;
    private Long parentId;
    private String fullId;
    private String label;
    private String iconClass;
    private String url;
    private String component;
    private Long authorityId;
    private Integer showOrder;
    private List<ResourceNode> children;

    public ResourceNode(){}

    public ResourceNode(Long id, Long parentId, String label) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
    }

    public List<ResourceNode> getChildren() {
        if (children == null) {
            children = Lists.newArrayList();
        }
        return children;
    }
}
