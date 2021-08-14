package ro.siit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.siit.domain.Pack;
import ro.siit.model.PackDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PackRepository extends JpaRepository<Pack, Long> {

    List<Pack> findAll();

    @Query("select new ro.siit.model.PackDto(p.id, p.title, p.targetCity, p.targetDistance, p.value, p.deliveryData) " +
            "from Pack p where p.title = :title ")
    Optional<PackDto> retrievePackByTitle(@Param("title") final String title);

    @Query("select new ro.siit.model.PackDto(p.id, p.title, p.targetCity, p.targetDistance, p.value, p.deliveryData) " +
    "from Pack p where p.targetCity = :city and p.deliveryData between current_timestamp and :date")
    List<PackDto> findByDateAndTitle(@Param("date") final LocalDateTime startDate, @Param("city") final String city);

    @Query("select SUM(p.value) " +
            "from Pack p where p.deliveryData between :date and :endDate")
    Optional<BigDecimal> findByDate(@Param("date") LocalDateTime date, @Param("endDate") LocalDateTime endDate);
}
