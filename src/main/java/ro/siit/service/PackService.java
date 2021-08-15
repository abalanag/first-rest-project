package ro.siit.service;

import ro.siit.model.PackDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PackService {
    PackDto findById(Long id);
    PackDto findByTitle(String title);
    Boolean deletePack(Long id);
    Boolean addPack(PackDto packDto);
    List<PackDto> findByDateAndTitle(LocalDateTime startData, String date);
    BigDecimal calculatePriceByDate(LocalDate date);
}
