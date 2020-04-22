package com.cupid.demo.common.entity;

import lombok.Data;

import java.util.Map;

/**
 * 说明：分页查询条件封装
 * @author cupid
 * @date 2020-04-17
 */
@Data
public class PageQueryResult {
    /**
     * 说明：当前页码
     * Integer 类型
     */
    private Integer currentPage;
    /**
     * 说明：每页显示条目数
     * Integer 类型
     */
    private Integer pageSize;
    /**
     * 说明：总条目数
     * Long 类型
     */
    private Long totalRows;
    /**
     * 说明：总页数
     * Long 类型
     */
    private Long totalPages;
    /**
     * 说明：查询结果Map
     * Map<String,Object>类型
     */
    private Map<String,Object> resultMap;
}
