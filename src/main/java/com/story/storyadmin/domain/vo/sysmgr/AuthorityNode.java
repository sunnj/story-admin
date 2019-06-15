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
public class AuthorityNode {
    private Long id;
    private Long parendId;
    private String label;
    private String code;
    private Integer showOrder;
    private List<AuthorityNode> children;

    public AuthorityNode(){}

    public AuthorityNode(Long id, Long parendId, String label) {
        this.id = id;
        this.parendId = parendId;
        this.label = label;
    }

    public List<AuthorityNode> getChildren() {
        if (children == null) {
            children = Lists.newArrayList();
        }
        return children;
    }
}
