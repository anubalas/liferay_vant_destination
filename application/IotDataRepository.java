import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotDataRepository extends JpaRepository<IotDataEntity, Long> {
    /**
     * Custom query to find historical data based on device ID and optional filters.
     *
     * @param deviceId   the ID of the device
     * @param startTime  optional start time for filtering data
     * @param endTime    optional end time for filtering data
     * @param deviceType optional device type for filtering data
     * @return List of IotDataEntity containing historical data
     */
    @Query("SELECT d FROM IotDataEntity d WHERE d.deviceId = :deviceId " +
           "AND (:startTime IS NULL OR d.timestamp >= :startTime) " +
           "AND (:endTime IS NULL OR d.timestamp <= :endTime) " +
           "AND (:deviceType IS NULL OR d.deviceType = :deviceType)")
    List<IotDataEntity> findHistoricalData(@Param("deviceId") String deviceId,
                                            @Param("startTime") String startTime,
                                            @Param("endTime") String endTime,
                                            @Param("deviceType") String deviceType);
}