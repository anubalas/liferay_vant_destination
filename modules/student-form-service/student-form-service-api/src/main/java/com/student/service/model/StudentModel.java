package com.student.service.model;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import com.example.rest.BaseModel;
import com.example.rest.AutoEscape;
import com.example.rest.ProviderType;

package com.example.rest;

@ProviderType
interface StudentModel extends BaseModel<Student> {

    /*
     * NOTE FOR DEVELOPERS:
     * Never modify or reference this interface directly. 
     * All methods that expect a student model instance should use the Student interface instead.
     */

    long getPrimaryKey();

    void setPrimaryKey(long primaryKey);

    long getStudentId();

    void setStudentId(long studentId);

    @AutoEscape
    String getName();

    void setName(String name);

    @AutoEscape
    String getEmail();

    void setEmail(String email);

    Student cloneWithOriginalValues();

    default String toXmlString() {
        return null;
    }
}