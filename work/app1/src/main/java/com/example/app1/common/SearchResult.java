package com.example.app1.common;

import java.util.List;

public class SearchResult<E> {
    // ページングFROM
    private int pageFrom;
    // ページングTO
    private int pageTo;
    // 現在のページ
    private int currentPage;
    // １ページあたりに表示する件数
    private int recordPerPage;
    // ユーザー情報一覧検索結果の総件数
    private int totalRecordCount;
    // ページ総数
    private int totalPageCount;
    // 汎用的List（今回はUser型のList）
    private List<E> entities;
    // ページ総数の計算
    public SearchResult(int totalRecordCount, int recordPerPage) {
        this.totalRecordCount = totalRecordCount;
        this.recordPerPage = recordPerPage;
        
        this.totalPageCount = (this.totalRecordCount / this.recordPerPage) +
                (this.totalRecordCount % this.recordPerPage > 0 ? 1 : 0);
    }
    // 
    public void moveTo(int page) {
        this.currentPage = page;
        this.pageFrom = Math.max(page - 1, 2);
        this.pageTo = Math.min(this.pageFrom + 2, this.totalPageCount - 1);
        // currentPage（現在ページ）がtotalPageCount（ページ総数）- 2より大きい場合、Fromがずれてしまうため再計算
        this.pageFrom = Math.max(pageTo - 2, 2);
    }
    
    public int getPageFrom() {
        return pageFrom;
    }
    public void setPageFrom(int pageFrom) {
        this.pageFrom = pageFrom;
    }
    public int getPageTo() {
        return pageTo;
    }
    public void setPageTo(int pageTo) {
        this.pageTo = pageTo;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getRecordPerPage() {
        return recordPerPage;
    }
    public void setRecordPerPage(int recordPerPage) {
        this.recordPerPage = recordPerPage;
    }
    public int getTotalRecordCount() {
        return totalRecordCount;
    }
    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }
    public int getTotalPageCount() {
        return totalPageCount;
    }
    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
    public List<E> getEntities() {
        return entities;
    }
    public void setEntities(List<E> entities) {
        this.entities = entities;
    }

}
