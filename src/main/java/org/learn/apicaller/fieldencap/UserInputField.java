package org.learn.apicaller.fieldencap;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jooq.Field;
import org.jooq.TableField;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.jooq.codegen.maven.apicaller.Tables.CUSTOMERS;

public enum UserInputField implements InputField {
    CUSTOMER_NUMBER("customerNumber", CUSTOMERS.getName(), CUSTOMERS.CUSTOMERNUMBER),
    CUSTOMER_NAME("customerName", CUSTOMERS.getName(), CUSTOMERS.CUSTOMERNAME),
    CONTACT_LAST_NAME("contactLastName", CUSTOMERS.getName(), CUSTOMERS.CONTACTLASTNAME),
    CONTACT_FIRST_NAME("contactFirstName", CUSTOMERS.getName(), CUSTOMERS.CONTACTFIRSTNAME),
    PHONE("phone", CUSTOMERS.getName(), CUSTOMERS.PHONE),
    ADDRESS_LINE1("addressL1", CUSTOMERS.getName(), CUSTOMERS.ADDRESSLINE1),
    ADDRESS_LINE2("addressL2", CUSTOMERS.getName(), CUSTOMERS.ADDRESSLINE2),
    CITY("city", CUSTOMERS.getName(), CUSTOMERS.CITY),
    STATE("state", CUSTOMERS.getName(), CUSTOMERS.STATE),
    POSTAL_CODE("postalCode", CUSTOMERS.getName(), CUSTOMERS.POSTALCODE),
    COUNTRY("country", CUSTOMERS.getName(), CUSTOMERS.COUNTRY),
    SALES_REP_EMP_NUMBER("salesRepId", CUSTOMERS.getName(), CUSTOMERS.SALESREPEMPLOYEENUMBER),
    CREDIT_LIMIT("creditLimit", CUSTOMERS.getName(), CUSTOMERS.CREDITLIMIT);

    private static final Set<Field<?>> CUSTOMER_COLS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            CUSTOMERS.CUSTOMERNUMBER,
            CUSTOMERS.CUSTOMERNAME,
            CUSTOMERS.CONTACTLASTNAME,
            CUSTOMERS.CONTACTFIRSTNAME,
            CUSTOMERS.PHONE,
            CUSTOMERS.ADDRESSLINE1,
            CUSTOMERS.ADDRESSLINE2,
            CUSTOMERS.CITY,
            CUSTOMERS.STATE,
            CUSTOMERS.POSTALCODE,
            CUSTOMERS.COUNTRY,
            CUSTOMERS.SALESREPEMPLOYEENUMBER,
            CUSTOMERS.CREDITLIMIT
    )));

    private final String fullyQualifiedName;
    private final String simpleName;
    private final String modelName;
    private final TableField<?, ?>[] columns;
    private final Set<InputField> children;

    UserInputField(String fullyQualifiedName, String modelName, Set<InputField> children, TableField<?, ?>... columns) {
        this.fullyQualifiedName = fullyQualifiedName;
        simpleName = InputField.super.getSimpleName(); // try changing this to this.simpleName, after all working ok
        this.modelName = modelName;
        this.columns = columns;
        this.children = Collections.unmodifiableSet(children);
    }

    UserInputField(String fullyQualifiedName, String modelName, TableField<?, ?>... columns) {
        this(fullyQualifiedName, modelName, Collections.emptySet(), columns);
    }

    @Override
    public String toString() { return fullyQualifiedName; }

    @Override
    public TableField<?, ?>[] getColumns() {
        return columns;
    }

    @Override @JsonValue
    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    @Override
    public Set<InputField> getChildren() {
        return children;
    }

    @Override
    public String getModelName() {
        return modelName;
    }
}
