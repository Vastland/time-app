package org.timeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.timeapp.domain.Times;
import org.timeapp.repo.TimesRepository;

@Service
public class TimesService {

    private final TimesRepository timesRepository;

    @Autowired
    public TimesService(TimesRepository timesRepository) {
        this.timesRepository = timesRepository;
    }

//    @Transactional
    public Times saveTimes(Times times) throws Exception {
        try {
            return timesRepository.save(times);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
