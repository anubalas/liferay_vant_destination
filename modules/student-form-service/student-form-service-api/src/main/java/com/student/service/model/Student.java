package com.student.service.model;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

package com.example.rest;

import com.student.service.model.impl.StudentImpl;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.StudentModel;
import com.liferay.portal.kernel.util.Accessor;

@ImplementationClassName("com.student.service.model.impl.StudentImpl")
@ProviderType
public interface Student extends PersistedModel, StudentModel {

    /*
     * NOTE FOR DEVELOPERS:
     * Never modify this interface directly. 
     * Add methods to com.student.service.model.impl.StudentImpl 
     * and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    static final Accessor<Student, Long> STUDENT_ID_ACCESSOR = new Accessor<Student, Long>() {

        @Override
        public Long get(Student student) {
            return student.getStudentId();
        }

        @Override
        public Class<Long> getAttributeClass() {
            return Long.class;
        }

        @Override
        public Class<Student> getTypeClass() {
            return Student.class;
        }
    };
}