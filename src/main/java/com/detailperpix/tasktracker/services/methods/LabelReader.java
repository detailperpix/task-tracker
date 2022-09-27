package com.detailperpix.tasktracker.services.methods;

import com.detailperpix.tasktracker.services.SQL;
import com.detailperpix.tasktracker.services.SQLiteDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabelReader {
    public static String getLabel(int labelId) {
        String sql = new SQL.Builder()
                .select(
                        new String[]{
                                "label"}

                ).from(new String[]{
                        "label"})
                .whereClause()
                .conditionClause(String.format("id = %d", labelId))
                .build();
        try (ResultSet rs = SQLiteDatabase.selectQuery(sql)){
            if (rs != null && rs.next()) {
                return rs.getString("label");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static int getTableSize() {
        String sql = new SQL.Builder()
                .select(new String[] {
                        "COUNT(*) as LENGTH"
                })
                .from(new String[]{"label"})
                .build();
        try (ResultSet rs = SQLiteDatabase.selectQuery(sql)) {
            if (rs != null && rs.next()) {
                return rs.getInt("LENGTH");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
