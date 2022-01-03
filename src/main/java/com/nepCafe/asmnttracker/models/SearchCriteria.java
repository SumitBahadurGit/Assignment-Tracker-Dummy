package com.nepCafe.asmnttracker.models;

public class SearchCriteria {

    private int page;
    private int size;
    private String orderBy;
    private int sortBy;
    private ATBody atBody;
    private User user;
    private String searchTerm;
    private String expiresFrom;
    private String expiresTo;
    private String status;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public ATBody getAtBody() {
        return atBody;
    }

    public void setAtBody(ATBody atBody) {
        this.atBody = atBody;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getExpiresFrom() {
        return expiresFrom;
    }

    public void setExpiresFrom(String expiresFrom) {
        this.expiresFrom = expiresFrom;
    }

    public String getExpiresTo() {
        return expiresTo;
    }

    public void setExpiresTo(String expiresTo) {
        this.expiresTo = expiresTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
