package com.student.service.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import com.example.rest.DynamicQuery;
import com.example.rest.ActionableDynamicQuery;
import com.example.rest.IndexableActionableDynamicQuery;
import com.example.rest.PortalException;
import com.example.rest.SystemException;
import com.example.rest.BaseLocalService;
import com.example.rest.PersistedModelLocalService;
import com.example.rest.OrderByComparator;

package com.example.rest;

@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor = {PortalException.class, SystemException.class})
public interface StudentLocalService extends BaseLocalService, PersistedModelLocalService {
    
    @Indexable(type = IndexableType.REINDEX)
    Student addStudent(Student student);

    PersistedModel createPersistedModel(Serializable primaryKeyObj) throws PortalException;

    @Transactional(enabled = false)
    Student createStudent(long studentId);

    @Override
    PersistedModel deletePersistedModel(PersistedModel persistedModel) throws PortalException;

    @Indexable(type = IndexableType.DELETE)
    Student deleteStudent(long studentId) throws PortalException;

    @Indexable(type = IndexableType.DELETE)
    Student deleteStudent(Student student);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    <T> T dslQuery(DSLQuery dslQuery);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    int dslQueryCount(DSLQuery dslQuery);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    DynamicQuery dynamicQuery();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start, int end);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start, int end, OrderByComparator<T> orderByComparator);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long dynamicQueryCount(DynamicQuery dynamicQuery);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long dynamicQueryCount(DynamicQuery dynamicQuery, Projection projection);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Student fetchStudent(long studentId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    ActionableDynamicQuery getActionableDynamicQuery();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

    String getOSGiServiceIdentifier();

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    PersistedModel getPersistedModel(Serializable primaryKeyObj) throws PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Student getStudent(long studentId) throws PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Student> getStudents(int start, int end);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    int getStudentsCount();

    @Indexable(type = IndexableType.REINDEX)
    Student updateStudent(Student student);
}