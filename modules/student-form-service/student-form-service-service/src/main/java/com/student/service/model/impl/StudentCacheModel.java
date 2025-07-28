package com.student.service.model.impl;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Externalizable;
import com.example.rest.HashUtil;
import com.example.rest.StringBundler;
import com.example.rest.CacheModel;
import com.example.rest.Student;

package com.example.rest;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class StudentCacheModel implements CacheModel<Student>, Externalizable {

    public long studentId;
    public String name;
    public String email;

    public StudentCacheModel() {
        // Initialize fields if necessary
    }

    @Override
    public void writeExternal(ObjectOutput out) {
        try {
            out.writeLong(studentId);
            out.writeUTF(name);
            out.writeUTF(email);
        } catch (Exception e) {
            // Handle serialization error
        }
    }

    @Override
    public void readExternal(ObjectInput in) {
        try {
            studentId = in.readLong();
            name = in.readUTF();
            email = in.readUTF();
        } catch (Exception e) {
            // Handle deserialization error
        }
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long id) {
        studentId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "StudentCacheModel{studentId=" + studentId + ", name='" + name + "', email='" + email + "'}";
    }

    public class StudentCacheModel {
        
        private long studentId;

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof StudentCacheModel)) {
                return false;
            }
            StudentCacheModel studentCacheModel = (StudentCacheModel) object;
            return studentId == studentCacheModel.studentId;
        }
    }

        @Override
        public int hashCode() {
            return HashUtil.hash(0, studentId);
        }

    class Student implements Externalizable {

        private long studentId;
        private String name;
        private String email;

        public void readExternal(ObjectInput objectInput) throws IOException {
            studentId = objectInput.readLong();
            name = objectInput.readUTF();
            email = objectInput.readUTF();
        }

        public void writeExternal(ObjectOutput objectOutput) throws IOException {
            objectOutput.writeLong(studentId);
            objectOutput.writeUTF(name);
            objectOutput.writeUTF(email);
        }
    }

    public Student toEntityModel() {
        StudentImpl studentImpl = new StudentImpl();
        studentImpl.setStudentId(studentId);

        if (name == null) {
            studentImpl.setName("");
        } else {
            studentImpl.setName(name);
        }

        if (email == null) {
            studentImpl.setEmail("");
        } else {
            studentImpl.setEmail(email);
        }

        studentImpl.resetOriginalValues();

        return studentImpl;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(7);

        sb.append("{studentId=").append(studentId);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append("}");

        return sb.toString();
    }

        public void writeExternal(ObjectOutput objectOutput) throws IOException {
            objectOutput.writeLong(studentId);
            if (name == null) {
                objectOutput.writeUTF("");
            } else {
                objectOutput.writeUTF(name);
            }
            if (email == null) {
                objectOutput.writeUTF("");
            } else {
                objectOutput.writeUTF(email);
            }
        }
}