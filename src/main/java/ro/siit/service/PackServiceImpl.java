package ro.siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.siit.domain.Pack;
import ro.siit.model.PackDto;
import ro.siit.repository.PackRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackServiceImpl implements PackService {

    @Autowired
    private PackRepository packRepository;

    @Override
    public List<PackDto> findAll() {
        return packRepository.findAll().stream()
                .map(p -> new PackDto(
                        p.getId(),
                        p.getTitle(),
                        p.getTargetCity(),
                        p.getTargetDistance(),
                        p.getValue(),
                        p.getDeliveryData()))
                .collect(Collectors.toList());
    }

    public PackDto findById(final Long id) {
        Pack pack = packRepository.findById(id)
                .orElse(new Pack(0L, "N/A", "N/A", BigDecimal.ZERO, BigDecimal.ZERO, LocalDateTime.now()));
        return new PackDto(pack.getId(), pack.getTitle(), pack.getTargetCity(), pack.getTargetDistance(), pack.getValue(), pack.getDeliveryData());
    }

    public PackDto findByTitle(final String title) {
        return packRepository.retrievePackByTitle(title).orElse(new PackDto(0L, "N/A", "N/A", BigDecimal.ZERO, BigDecimal.ZERO, LocalDateTime.now()));
    }

    public Boolean deletePack(final Long id) {
        packRepository.findById(id).ifPresent(p -> packRepository.delete(p));
        return packRepository.findById(id).isEmpty();
    }

    public Boolean addPack(final PackDto packDto) {
        packRepository.save(new Pack(packDto.getId(), packDto.getTitle(), packDto.getTargetCity(), packDto.getTargetDistance(), packDto.getValue(), packDto.getDeliveryData()));
        return packRepository.findById(packDto.getId()).isPresent();
    }

    public List<PackDto> findByDateAndTitle(final LocalDateTime startDate, final String city) {
        List<PackDto> pack = packRepository.findByDateAndTitle(startDate, city);
        return pack;
    }

    public BigDecimal calculatePriceByDate(LocalDate date) {
        BigDecimal i = packRepository.findByDate(date.atStartOfDay(), date.atStartOfDay().plusDays(1)).orElse(BigDecimal.ZERO);
        return i;
    }
}

