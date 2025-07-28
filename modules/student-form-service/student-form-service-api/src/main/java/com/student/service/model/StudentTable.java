package com.student.service.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.BaseTable;
import jakarta.sql.Types;

package com.example.rest;

import java.sql.Types;

public class StudentTable extends BaseTable<StudentTable> {

    // Singleton instance of StudentTable
    public static final StudentTable INSTANCE = new StudentTable();

    // Column definitions
    public final Column<StudentTable, Long> studentId = createColumn(
        "studentId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);

    public final Column<StudentTable, String> name = createColumn(
        "name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

    public final Column<StudentTable, String> email = createColumn(
        "email", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

    // Method to create a column
    public <T> Column<StudentTable, T> createColumn(String columnName, Class<T> columnType, int sqlType, int columnFlag) {
        return new Column<StudentTable, T>(columnName, columnType, sqlType, columnFlag);
    }

    package com.example.rest;

    public class StudentTable extends Superclass {

        private StudentTable() {
            super("Student_Student", StudentTable::new);
        }
    }
}