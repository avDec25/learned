package org.learn.apicaller.fieldencap;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.codegen.maven.apicaller.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @Autowired
    DSLContext dslContext;

    @PostMapping("fetchFields")
    public ResponseEntity<?> fetchFields(@RequestBody UserInfoRequest userInfoRequest) {
        for (UserInputField field : userInfoRequest.getFields()) {
            System.out.printf("%s: %s\n", field.getFullyQualifiedName(), field.getModelName());

            Result<Record> result = dslContext.select(field.getColumns())
                    .from(field.getModelName())
                    .where(Tables.CUSTOMERS.CUSTOMERNUMBER.eq(userInfoRequest.customerNumber)).fetch();
            System.out.println(result.stream().toList().get(0));

            System.out.println("===============================================================================");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
