package com.practice.vpalagin.project.repository;

import com.practice.vpalagin.project.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
}
