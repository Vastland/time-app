package org.timeapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.timeapp.domain.Times;

@Repository
public interface TimesRepository extends MongoRepository<Times, String> {
}
