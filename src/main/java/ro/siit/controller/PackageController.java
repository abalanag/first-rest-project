package ro.siit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.siit.model.PackDto;
import ro.siit.service.PackService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Api(value = "API for handling packages")
@RestController
public class PackageController {

    @Autowired
    private PackService packService;

    @ApiOperation(value = "Endpoint which a package by id")
    @GetMapping("package-management/packages/{packId}")
    public @ResponseBody
    PackDto getPackById(@Valid @PathVariable("packId") final Long id) {
        return packService.findById(id);
    }

    @GetMapping("package-management/packages/title/{packTitle}")
    public @ResponseBody
    PackDto findPack(@Valid @PathVariable("packTitle") final String title) {
        return packService.findByTitle(title);
    }

    @GetMapping("package-management/packages")
    public @ResponseBody
    List<PackDto> findPack(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime date,
            @RequestParam("target-city") final String targetCity) {
        return packService.findByDateAndTitle(date, targetCity);
    }

    @GetMapping("package-management/packages/{date}/prices/calculate")
    public @ResponseBody
    String calculatePriceByDate(
            @PathVariable("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate date) {
        return "Total total packages price for the date " + date + " is: " + packService.calculatePriceByDate(date) + "€";
    }

    @PostMapping("package-management/packages")
    public ResponseEntity<String> addPack(@Valid @RequestBody final PackDto packDto) {
        if (packService.addPack(packDto)) {
            return ResponseEntity.ok("Package was saved");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The package was not saved");
    }

    @DeleteMapping("package-management/packages/{packId}")
    public ResponseEntity<String> deletePack(@Valid @PathVariable("packId") final Long id, @RequestBody final PackDto packDto) {
        if (packService.deletePack(id)) {
            return ResponseEntity.ok("Package was deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The package was not deleted");
    }
}
