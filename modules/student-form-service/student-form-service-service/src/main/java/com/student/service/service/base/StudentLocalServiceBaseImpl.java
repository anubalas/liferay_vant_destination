package com.student.service.service.base;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.osgi.service.component.annotations.Reference;
import com.example.rest.StudentPersistence;
import com.example.rest.CounterLocalService;
import com.example.rest.ClassNameLocalService;
import com.example.rest.ResourceLocalService;
import com.example.rest.UserLocalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

package com.example.rest;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Reference;

public abstract class StudentLocalServiceBaseImpl extends BaseLocalServiceImpl implements AopService, IdentifiableOSGiService, StudentLocalService {

    protected StudentLocalService studentLocalService;

    @Reference
    protected StudentPersistence studentPersistence;

    @Reference
    protected CounterLocalService counterLocalService;

    @Reference
    protected ClassNameLocalService classNameLocalService;

    @Reference
    protected ResourceLocalService resourceLocalService;

    @Reference
    protected UserLocalService userLocalService;

    private static final Log _log = LogFactoryUtil.getLog(StudentLocalServiceBaseImpl.class);

    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Student addStudent(Student student) {
        student.setNew(true);
        return studentPersistence.update(student);
    }

    @Override
    public PersistedModel createPersistedModel(Serializable primaryKeyObj) throws PortalException {
        Long primaryKey = (Long) primaryKeyObj;
        PersistedModel newModel = studentPersistence.create(primaryKey.longValue());
        return newModel;
    }

    @Override
    @Transactional(enabled = false)
    public Student createStudent(long studentId) {
        return studentPersistence.create(studentId);
    }

        @Deactivate
        protected void deactivate() {
            // No actions are performed during the deactivation process
        }

        public PersistedModel deletePersistedModel(PersistedModel persistedModel) throws PortalException {
            if (_log.isWarnEnabled()) {
                _log.warn("Implement StudentLocalServiceImpl#deleteStudent(Student) to avoid orphaned data");
            }

            Student student = (Student) persistedModel;
            return studentLocalService.deleteStudent(student);
        }

    @Indexable(type = IndexableType.DELETE)
    function deleteStudent(student: Student) -> Student:
        returnValue = studentPersistence.remove(student)
        return returnValue

    @Indexable(type = IndexableType.DELETE)
    @Override
    public Student deleteStudent(long studentId) throws PortalException {
        return studentPersistence.remove(studentId);
    }

    @Override
    <T> dslQuery(DSLQuery dslQuery) {
        return studentPersistence.dslQuery(dslQuery);
    }

    @Override
    public int dslQueryCount(DSLQuery dslQuery) {
        Long count;
        count = dslQuery(dslQuery);
        return count != null ? count.intValue() : 0;
    }

    @Override
    public DynamicQuery dynamicQuery() {
        Class<?> clazz = getClass();

        DynamicQuery dynamicQueryObject = DynamicQueryFactoryUtil.forClass(
            Student.class, 
            clazz.getClassLoader()
        );

        return dynamicQueryObject;
    }

    @Override
    public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
        return studentPersistence.findWithDynamicQuery(dynamicQuery);
    }

    @Override
    public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start, int end) {
        return studentPersistence.findWithDynamicQuery(dynamicQuery, start, end);
    }

    public <T> List<T> dynamicQuery(
            DynamicQuery dynamicQuery, 
            int start, 
            int end, 
            OrderByComparator<T> orderByComparator) {
        
        return studentPersistence.findWithDynamicQuery(
                dynamicQuery, 
                start, 
                end, 
                orderByComparator);
    }

    @Override
    public long dynamicQueryCount(DynamicQuery dynamicQuery) {
        return studentPersistence.countWithDynamicQuery(dynamicQuery);
    }

    @Override
    public long dynamicQueryCount(DynamicQuery dynamicQuery, Projection projection) {
        return studentPersistence.countWithDynamicQuery(dynamicQuery, projection);
    }

        public Student fetchStudent(long studentId) {
            return studentPersistence.fetchByPrimaryKey(studentId);
        }

    @Override
    public ActionableDynamicQuery getActionableDynamicQuery() {
        DefaultActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

        actionableDynamicQuery.setBaseLocalService(studentLocalService);
        actionableDynamicQuery.setClassLoader(getClassLoader());
        actionableDynamicQuery.setModelClass(Student.class);
        actionableDynamicQuery.setPrimaryKeyPropertyName("studentId");

        return actionableDynamicQuery;
    }

    public Class<?>[] getAopInterfaces() {
        return new Class<?>[] {
            StudentLocalService.class,
            IdentifiableOSGiService.class,
            PersistedModelLocalService.class
        };
    }

    @Override
    public BasePersistence<Student> getBasePersistence() {
        return studentPersistence;
    }

    @Override
    public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
        IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();
        indexableActionableDynamicQuery.setBaseLocalService(studentLocalService);
        indexableActionableDynamicQuery.setClassLoader(getClassLoader());
        indexableActionableDynamicQuery.setModelClass(Student.class);
        indexableActionableDynamicQuery.setPrimaryKeyPropertyName("studentId");
        return indexableActionableDynamicQuery;
    }

    protected Class<?> getModelClass() {
        return Student.class;
    }

    protected String getModelClassName() {
        return Student.class.getName();
    }

    @Override
    public String getOSGiServiceIdentifier() {
        return StudentLocalService.class.getName();
    }

    @Override
    public PersistedModel getPersistedModel(Serializable primaryKeyObj) throws PortalException {
        PersistedModel result;
        result = studentPersistence.findByPrimaryKey(primaryKeyObj);
        return result;
    }

    @Override
    public Student getStudent(long studentId) throws PortalException {
        return studentPersistence.findByPrimaryKey(studentId);
    }

    public List<Student> getStudents(int start, int end) {
        return studentPersistence.findAll(start, end);
    }

    @Override
    public int getStudentsCount() {
        return studentPersistence.countAll();
    }

    protected void initActionableDynamicQuery(ActionableDynamicQuery actionableDynamicQuery) {
        actionableDynamicQuery.setBaseLocalService(studentLocalService);
        actionableDynamicQuery.setClassLoader(getClassLoader());
        actionableDynamicQuery.setModelClass(Student.class);
        actionableDynamicQuery.setPrimaryKeyPropertyName("studentId");
    }

        public void runSQL(String sql) {
            DataSource dataSource = studentPersistence.getDataSource();
            DB db = DBManagerUtil.getDB();
            Connection currentConnection = CurrentConnectionUtil.getConnection(dataSource);
            
            try {
                if (currentConnection != null) {
                    db.runSQL(currentConnection, new String[] {sql});
                    return;
                }
                try (Connection connection = dataSource.getConnection()) {
                    db.runSQL(connection, new String[] {sql});
                }
            } catch (Exception exception) {
                throw new SystemException(exception);
            }
        }

        @Override
        public void setAopProxy(Object aopProxy) {
            studentLocalService = (StudentLocalService) aopProxy;
        }

    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Student updateStudent(Student student) {
        return studentPersistence.update(student);
    }
}