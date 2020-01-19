package com.sy.model.page;

/**
 *计算分页工具
 * @author shenyang
 * @date 2020/1/19
 *  * @param null
 * @return
 */
public class ToolPaging {

    public static int howManyPage(int allCount, int pageSize) {
        if (pageSize == 0) {
            return 0;
        }
        int page = allCount / pageSize;
        if (allCount % pageSize > 0) {
            page++;
        }
        return page;
    }



}
