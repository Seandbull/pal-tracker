package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class TimeEntryController {

    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    @ResponseBody
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate){

        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeId}")
    @ResponseBody
    public ResponseEntity<TimeEntry> read(@PathVariable long timeId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeId);
        if (timeEntry == null) {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
        }
    }

    @GetMapping ("/time-entries")
    @ResponseBody
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>>(list, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    @ResponseBody
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry update = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if (update == null){
            return new ResponseEntity<TimeEntry>(update, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<TimeEntry>(update, HttpStatus.OK);
        }
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
