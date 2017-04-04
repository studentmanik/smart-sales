package com.studentmanik.smartsales.Sync;

/**
 * Created by Ritu on 9/8/2016.
 */
public class DataSync {
    String serviceUrl;
    String httpMethod;
    String tableName;
    String uniqueColumn;
    String updateColumn;
    String whereCondition;


    public DataSync(String serviceUrl, String httpMethod, String tableName, String uniqueColumn, String updateColumn, String whereCondition) {
        this.serviceUrl = serviceUrl;
        this.httpMethod = httpMethod;
        this.tableName = tableName;
        this.uniqueColumn = uniqueColumn;
        this.updateColumn = updateColumn;
        this.whereCondition = whereCondition;
    }

    public DataSync() {
    }

    public DataSync(String serviceUrl, String httpMethod) {
        this.serviceUrl = serviceUrl;
        this.httpMethod = httpMethod;
    }

    public DataSync(String serviceUrl, String httpMethod, String uniqueColumn, String whereCondition) {
        this.serviceUrl = serviceUrl;
        this.httpMethod = httpMethod;
        this.uniqueColumn = uniqueColumn;
        this.whereCondition = whereCondition;
    }

    public DataSync(String serviceUrl, String httpMethod, String tableName, String uniqueColumn, String updateColumn) {
        this.serviceUrl = serviceUrl;
        this.httpMethod = httpMethod;
        this.tableName = tableName;
        this.uniqueColumn = uniqueColumn;
        this.updateColumn = updateColumn;
    }

    public String getWhereCondition() {
        return whereCondition;
    }

    public void setWhereCondition(String whereCondition) {
        this.whereCondition = whereCondition;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUniqueColumn() {
        return uniqueColumn;
    }

    public void setUniqueColumn(String uniqueColumn) {
        this.uniqueColumn = uniqueColumn;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUpdateColumn() {
        return updateColumn;
    }

    public void setUpdateColumn(String updateColumn) {
        this.updateColumn = updateColumn;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
}
