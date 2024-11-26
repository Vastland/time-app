package org.timeapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.timeapp.domain.Times;

@Repository
public interface TimesRepository extends JpaRepository<Times, Integer> {
}
