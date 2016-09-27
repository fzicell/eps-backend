package hu.icell.eps.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import hu.icell.eps.model.Parking;

@Transactional
public interface ParkingDAO extends CrudRepository<Parking, Integer> {
	
	public Parking findByParkingId(Integer parkingId);
	public List<Parking> findByCustomerId(Integer customerId);
	
    @Modifying(clearAutomatically =true)
    @Query("SELECT po FROM Parking po WHERE po.customerId = :customerId AND po.finishedAt = null")
    public List<Parking> listActiveParkings(@Param("customerId") Integer customerId);
	
    @Modifying(clearAutomatically =true)
    @Query("UPDATE Parking po SET po.finishedAt = CURRENT_TIMESTAMP WHERE po.parkingId = :parkingId")
    public void stopParking(@Param("parkingId") Integer parkingId);
	
}
