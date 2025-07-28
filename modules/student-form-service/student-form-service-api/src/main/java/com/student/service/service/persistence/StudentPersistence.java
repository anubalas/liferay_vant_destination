package com.student.service.service.persistence;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.util.List;

package com.example.rest;

import java.util.List;

@ProviderType
public interface StudentPersistence extends BasePersistence<Student> {

    /**
     * Caches a single Student object in the entity cache if enabled.
     *
     * @param student the Student object to cache
     */
    void cacheResult(Student student);

    /**
     * Caches a list of Student objects in the entity cache if enabled.
     *
     * @param students the list of Student objects to cache
     */
    void cacheResult(List<Student> students);

    /**
     * Creates a new Student object with the specified primary key.
     *
     * @param studentId the primary key for the new Student
     * @return the newly created Student object
     */
    Student create(long studentId);

    /**
     * Removes a Student object from the database using its primary key.
     *
     * @param studentId the primary key of the Student to remove
     * @return the removed Student object
     * @throws NoSuchStudentException if the Student cannot be found
     */
    Student remove(long studentId) throws NoSuchStudentException;

    /**
     * Updates the implementation of a Student object.
     *
     * @param student the Student object to update
     */
    void updateImpl(Student student);

    /**
     * Retrieves a Student object by its primary key.
     *
     * @param studentId the primary key of the Student to retrieve
     * @return the Student object
     * @throws NoSuchStudentException if the Student cannot be found
     */
    Student findByPrimaryKey(long studentId) throws NoSuchStudentException;

    /**
     * Fetches a Student object by its primary key, returning null if not found.
     *
     * @param studentId the primary key of the Student to fetch
     * @return the Student object or null if not found
     */
    Student fetchByPrimaryKey(long studentId);

    /**
     * Returns a list of all Student objects.
     *
     * @return a list of all Student objects
     */
    List<Student> findAll();

    /**
     * Returns a range of Student objects for pagination.
     *
     * @param start the lower bound of the range of Student objects
     * @param end   the upper bound of the range of Student objects (not inclusive)
     * @return a list of Student objects in the specified range
     */
    List<Student> findAll(int start, int end);

    /**
     * Returns an ordered range of Student objects for pagination.
     *
     * @param start            the lower bound of the range of Student objects
     * @param end              the upper bound of the range of Student objects (not inclusive)
     * @param orderByComparator the comparator to order the results
     * @return a list of ordered Student objects in the specified range
     */
    List<Student> findAll(int start, int end, OrderByComparator<Student> orderByComparator);

    /**
     * Returns an ordered range of Student objects for pagination with cache option.
     *
     * @param start            the lower bound of the range of Student objects
     * @param end              the upper bound of the range of Student objects (not inclusive)
     * @param orderByComparator the comparator to order the results
     * @param useFinderCache   whether to use the finder cache
     * @return a list of ordered Student objects in the specified range
     */
    List<Student> findAll(int start, int end, OrderByComparator<Student> orderByComparator, boolean useFinderCache);

    /**
     * Removes all Student objects from the database.
     */
    void removeAll();

    /**
     * Returns the total number of Student objects in the database.
     *
     * @return the total number of Student objects
     */
    int countAll();
}