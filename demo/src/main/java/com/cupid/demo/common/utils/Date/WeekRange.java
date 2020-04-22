package com.cupid.demo.common.utils.Date;

import lombok.Data;

import java.util.Date;

/**
 * @author cupid
 * @date 2020/04/21
 */
@Data
public class WeekRange {
    /**
     * 周开始日
     */
    private Date begin;
    /**
     * 周结束日
     */
    private Date end;

    /**
     * 构造
     * @param begin 周开始日
     */
    public WeekRange(Date begin) {
        super();
        this.begin = begin;
    }

    /**
     * 构造
     * @param begin 周开始日
     * @param end 周结束日
     */
    public WeekRange(Date begin, Date end) {
        super();
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "WeekRange{" +
                "begin=" + begin +
                ", end=" + end +
                '}';
    }
}
