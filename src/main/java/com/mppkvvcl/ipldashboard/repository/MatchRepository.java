package com.mppkvvcl.ipldashboard.repository;

import com.mppkvvcl.ipldashboard.model.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    public List<Match> findByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    public List<Match> findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(String teamName1,LocalDate startDate1, LocalDate endDate1,String teamName2, LocalDate startDate, LocalDate endDate);
}
