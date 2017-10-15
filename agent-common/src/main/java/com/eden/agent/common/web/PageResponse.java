package com.eden.agent.common.web;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liyuanhang on 2017/4/22.
 */
public class PageResponse<T> extends BaseResponse<PageResponse.PageWrapper<T>> implements Serializable {

    private int pageNo;

    private int pageSize;

    private int totalCount;

    public PageResponse(List<T> data, int pageNo, int pageSize, int totalCount) {
        super(null);
        PageWrapper<T> pageWrapper = new PageWrapper<>(pageNo, pageSize, totalCount, data);
        setData(pageWrapper);
    }

    public static class PageWrapper<T> {

        private int pageNo;

        private int pageSize;

        private int totalCount;

        private List<T> dataList;

        public PageWrapper(int pageNo, int pageSize, int totalCount, List<T> dataList) {
            this.pageNo = pageNo;
            this.pageSize = pageSize;
            this.totalCount = totalCount;
            this.dataList = dataList;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<T> getDataList() {
            return dataList;
        }

        public void setDataList(List<T> dataList) {
            this.dataList = dataList;
        }
    }

}
