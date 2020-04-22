package com.cupid.demo.common.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 说明：分页查询条件封装
 * @author cupid
 * @date 2020-04-17
 */
@Data
public class PageQueryCondition {

    /**
     * 说明：当前页码
     * Integer类型
     */
    private Integer currentPage = 0;
    /**
     * 说明：每页显示条目数
     * Integer 类型
     */
    private Integer pageSize = 10;
    /**
     * 说明：查询条件Map
     * Map<String,Object>类型
     */
    private Map<String,Object> conditionMap;
}
