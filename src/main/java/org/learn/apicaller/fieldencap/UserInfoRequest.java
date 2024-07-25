package org.learn.apicaller.fieldencap;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

public class UserInfoRequest {
    @JsonProperty("customerNumber")
    public Integer customerNumber;

    @JsonProperty("fields")
    public Set<UserInputField> fields = new HashSet<>();

    public UserInfoRequest fields(Set<UserInputField> fields) {
        this.fields = fields;
        return this;
    }

    public UserInfoRequest addField(UserInputField field) {
        this.fields.add(field);
        return this;
    }

    public Set<UserInputField> getFields() {
        return fields;
    }

    public void setFields(Set<UserInputField> fields) {
        this.fields = fields;
    }
}
