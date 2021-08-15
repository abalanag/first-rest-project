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

    @ApiOperation(value = "Endpoint which returns a package by giving the id")
    @GetMapping("/packages/{packId}")
    public @ResponseBody
    PackDto getPackById(@Valid @PathVariable("packId") final Long id) {
        return packService.findById(id);
    }

    @ApiOperation(value = "Endpoint which returns a package by giving the title")
    @GetMapping("/packages/title/{packTitle}")
    public @ResponseBody
    PackDto findPack(@Valid @PathVariable("packTitle") final String title) {
        return packService.findByTitle(title);
    }

    @ApiOperation(value = "Endpoint which returns a list of packs that will need to be delivered" +
            "starting from this point of time until the date parameter and the city")
    @GetMapping("/packages")
    public @ResponseBody
    List<PackDto> findPack(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime date,
            @RequestParam("target-city") final String targetCity) {
        return packService.findByDateAndTitle(date, targetCity);
    }

    @ApiOperation(value = "Endpoint that calculates the sum of all the packages delivered on a specified day given by the date parameter")
    @GetMapping("/packages/{date}/prices/calculate")
    public @ResponseBody
    String calculatePriceByDate(
            @PathVariable("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate date) {
        return "Total total packages price for the date " + date + " is: " + packService.calculatePriceByDate(date) + "€";
    }

    @ApiOperation(value = "Endpoint that will save a new package by giving it all the needed information")
    @PostMapping("/packages")
    public ResponseEntity<String> addPack(@Valid @RequestBody final PackDto packDto) {
        if (packService.addPack(packDto)) {
            return ResponseEntity.ok("Package was saved");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The package was not saved");
    }

    @ApiOperation(value = "Endpoint that will delete a package by giving it the id of the package that will need to be deleted")
    @DeleteMapping("/packages/{packId}")
    public ResponseEntity<String> deletePack(@Valid @PathVariable("packId") final Long id, @RequestBody final PackDto packDto) {
        if (packService.deletePack(id)) {
            return ResponseEntity.ok("Package was deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The package was not deleted");
    }
}
