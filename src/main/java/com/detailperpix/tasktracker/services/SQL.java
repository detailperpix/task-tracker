package com.detailperpix.tasktracker.services;

/**
 * SQLBuilder for complex SQL scripts
 */
public class SQL {

    private SQL() {
    }
    public static class Builder {
        private String sql;
        public Builder() {
            sql = "";
        }
        // select clause
        public Builder select(String[] properties) {
            sql = sql
                    + "SELECT "
                    + String.join(", ", properties);
            return this;
        }

        public Builder from(String[] tableName) {
            sql = sql + " FROM " + String.join(", ", tableName);
            return this;
        }


        public Builder insertClause(String tableName) {
            sql = "INSERT INTO " + tableName;
            return this;
        }

        public Builder tableColumn(String[] columns) {
            sql = sql + String.format("(%s)", String.join(", ", columns)) + " ";
            return this;
        }

        // where clause
        public Builder whereClause() {
            sql = sql + " WHERE ";
            return this;
        }

        public Builder conditionClause(String condition) {
            sql = sql + condition;
            return this;
        }

        public Builder andClause(String condition) {
            sql = sql + " AND " + condition;
            return this;
        }

        public Builder orClause(String condition) {
            sql = sql + " OR " + condition;
            return this;
        }

        public Builder openClause() {
            sql = sql + " (";
            return this;
        }

        public Builder closeClause() {
            sql = sql + ") ";
            return this;
        }

        // update clause
        public Builder updateClause(String tableName) {
            sql = sql +" UPDATE " + tableName + " SET ";
            return this;
        }

        public Builder valuesClause(String[] values) {
            sql = sql
                    + "VALUES("
                    + String.join(", ", values)
                    +")";
            return this;
        }

        // create table clause
        // include if not exists
        private Builder createTable(String tableName, boolean includeIfNotExists) {
            sql = sql + "CREATE TABLE ";
            if (includeIfNotExists) {
                sql = sql + "IF NOT EXISTS ";
            }
            sql = sql + tableName + " ";
            return this;
        }

        public Builder createTableIfNotExists(String tableName) {
            return createTable(tableName, true);
        }

        public Builder createTable(String tableName) {
            return createTable(tableName, false);
        }

        private Builder dropTable(String tableName, boolean includeIfExists) {
            sql = sql + "DROP TABLE ";
            if (includeIfExists) {
                sql = sql + "IF EXISTS ";
            }
            sql = sql + tableName + " ";
            return this;
        }

        public Builder dropTableIfExists(String tableName) {
            return dropTable(tableName, true);
        }

        public Builder dropTable(String tableName) {
            return dropTable(tableName, false);
        }
        public String build() {
            return this.sql;
        }



    }
}

