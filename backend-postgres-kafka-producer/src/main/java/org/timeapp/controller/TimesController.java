package org.timeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.timeapp.domain.Times;
import org.timeapp.service.KafkaSendMessageService;
import org.timeapp.service.TimesService;

@RestController
@RequestMapping("/times")
public class TimesController {

    private final TimesService timesService;

    private final KafkaSendMessageService kafkaSendMessageService;

    @Autowired
    public TimesController(TimesService timesService, KafkaSendMessageService kafkaSendMessageService) {
        this.timesService = timesService;
        this.kafkaSendMessageService = kafkaSendMessageService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> saveTimes(@RequestBody Times times) {
        timesService.saveTimes(times);
        kafkaSendMessageService.sendTimes(times);
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<String> getTimes() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Times> getTime(@PathVariable("id") int id) throws Exception {
        Times timesById = timesService.findTimesById(id);
        return new ResponseEntity<>(timesById, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTimes(@PathVariable("id") int id) throws Exception {
        timesService.deleteTimesById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
