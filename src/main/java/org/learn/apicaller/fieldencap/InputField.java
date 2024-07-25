package org.learn.apicaller.fieldencap;

import org.jooq.Field;
import org.jooq.TableField;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface InputField {

    Pattern SIMPLE_NAME_GROUP_PATTERN = Pattern.compile("^(?:\\w+\\.)*(\\w+)$");

    static Map<String, Set<Field<?>>> getColumnsByTableName(Collection<? extends InputField> fields) {
        if (fields == null) {
            return Collections.emptyMap();
        }

        Map<String, Set<Field<?>>> fieldsByTableName = new HashMap<>();

        for (InputField field : fields) {
            for (TableField<?, ?> column : field.getColumns()) {
                fieldsByTableName.computeIfAbsent(column.getTable().getName(), set -> new HashSet<>()).add(column);
            }

            for (InputField child : field.getChildren()) {
                for (TableField<?, ?> column : field.getColumns()) {
                    fieldsByTableName.computeIfAbsent(column.getTable().getName(), set -> new HashSet<>()).add(column);
                }
            }
        }

        return fieldsByTableName;
    }

    default String getSimpleName() {
        Matcher matcher = SIMPLE_NAME_GROUP_PATTERN.matcher(getFullyQualifiedName());
        return matcher.matches() ? matcher.group(1) : "";
    }

    TableField<?, ?>[] getColumns();

    String getFullyQualifiedName();

    Set<InputField> getChildren();

    String getModelName();

}
