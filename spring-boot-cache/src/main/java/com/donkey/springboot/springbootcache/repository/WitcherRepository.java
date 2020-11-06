package com.donkey.springboot.springbootcache.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WitcherRepository extends JpaRepository<Witcher, Long> {

    @Cacheable("school_witcher")
    Optional<Witcher> findById(long id);

    @Query("SELECT w FROM Witcher w WHERE w.id = :id")
    Optional<Witcher> findByIdWithoutCache(@Param("id") long id);

    @Transactional
    @Modifying
    @Query("UPDATE Witcher w SET w.race = :race WHERE w.id = :id")
    void updateRaceById(@Param("id") long id, @Param("race") String race);
}
