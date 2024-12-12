package org.timeapp.integration.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.timeapp.config.TestConfig;
import org.timeapp.domain.Times;
import org.timeapp.repo.TimesRepository;

import java.time.Instant;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class TimesTest {

    @Autowired
    private TimesRepository timesRepository;

    @Test
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @DirtiesContext
    void createTimeThenGetOk() {
        Times times = new Times();
        times.setTime("15:19:00");
        times.setCreatedAt(Instant.now());
        timesRepository.save(times);

        Times timesResult = timesRepository.findById(1).orElseThrow();
        assertEquals(times.getId(), timesResult.getId(), "Ids are not equals");
        assertEquals(times.getTime(), timesResult.getTime(), "Times are not equals");
        assertEquals(times.getCreatedAt(), timesResult.getCreatedAt(), "CreatedAt are not equals");
    }

    @Test
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @DirtiesContext
    void updateTimeThenGetOk() {
        Times times = new Times();
        times.setTime("15:19:00");
        times.setCreatedAt(Instant.now());
        timesRepository.save(times);

        Times timesResult = timesRepository.findById(1).orElseThrow();
        assertEquals(times.getId(), timesResult.getId(), "Ids are not equals");
        assertEquals(times.getTime(), timesResult.getTime(), "Times are not equals");
        assertEquals(times.getCreatedAt(), timesResult.getCreatedAt(), "CreatedAt are not equals");

        Instant now = Instant.now();
        times.setTime("16:20:00");
        times.setCreatedAt(now);
        timesRepository.save(times);

        timesResult = timesRepository.findById(1).orElseThrow();
        assertEquals(times.getId(), timesResult.getId(), "Ids are not equals");
        assertEquals(times.getTime(), timesResult.getTime(), "Times are not equals");
        assertEquals(times.getCreatedAt(), now, "CreatedAt are not equals");
    }

    @Test
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @DirtiesContext
    void deleteTimeThenGetOk() {
        Times times = new Times();
        times.setTime("15:19:00");
        times.setCreatedAt(Instant.now());
        timesRepository.save(times);

        Times timesResult = timesRepository.findById(1).orElseThrow();
        assertEquals(times.getId(), timesResult.getId(), "Ids are not equals");
        assertEquals(times.getTime(), timesResult.getTime(), "Times are not equals");
        assertEquals(times.getCreatedAt(), timesResult.getCreatedAt(), "CreatedAt are not equals");

        timesRepository.delete(times);
        assertThrows(NoSuchElementException.class, () -> timesRepository.findById(1).orElseThrow());
    }
}

