package org.timeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.timeapp.domain.Times;
import org.timeapp.repo.TimesRepository;

@Service
public class TimesService {

    private final TimesRepository timesRepository;

    @Autowired
    public TimesService(TimesRepository timesRepository) {
        this.timesRepository = timesRepository;
    }

    @Transactional
    public Times saveTimes(Times times) {
        Times timesToDb = new Times();
        timesToDb.setTime(times.getTime());
        timesToDb.setCreatedAt(times.getCreatedAt());
        return timesRepository.save(timesToDb);
    }

    @Transactional
    public Times updateTimes(Times times) {
        return timesRepository.save(times);
    }

    @Transactional
    public void deleteTimes(Times times) {
        timesRepository.delete(times);
    }

    @Transactional
    public Times findTimesById(int id) throws Exception {
        return timesRepository.findAll().stream()
                .filter(times -> times.getId().equals(id)).findFirst().orElseThrow(Exception::new);
    }

    @Transactional
    public void deleteTimesById(int id) throws Exception {
        Times timesToDelete = timesRepository.findAll().stream()
                .filter(times -> times.getId().equals(id)).findFirst().orElseThrow(Exception::new);
        timesRepository.delete(timesToDelete);
    }
}
