package cn.itcast.travel.domain;

import java.util.List;

public class PageBean<T> {
    private int currPage;
    private int totalPage;
    private int totalCount;
    private int pageSize;
    private List<T> list;

    public PageBean() {
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currPage=" + currPage +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }

    public PageBean(int currPage, int totalPage, int totalCount, int pageSize, List<T> list) {
        this.currPage = currPage;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.list = list;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
