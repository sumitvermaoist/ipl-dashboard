package com.mppkvvcl.ipldashboard.controller;

import com.mppkvvcl.ipldashboard.model.Match;
import com.mppkvvcl.ipldashboard.model.Team;
import com.mppkvvcl.ipldashboard.repository.MatchRepository;
import com.mppkvvcl.ipldashboard.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("team/")
@CrossOrigin
public class TeamController {

    private static final Logger log = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{teamName}")
    public Team getTeam(@PathVariable("teamName") String teamName) {
        final String methodName = "getTeam() : ";
        log.info(methodName + "called");

        Team team = teamRepository.findByTeamName(teamName);

        Pageable pageable = PageRequest.of(0,4);

        team.setMatches(matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName,teamName,pageable));

        return team;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable("teamName") String teamName, @RequestParam("year") int year) {
        final String methodName = "getMatchesForTeam() : ";
        log.info(methodName + "called");

        LocalDate startDate = LocalDate.of(year,1,1);
        LocalDate endDate = LocalDate.of(year+1,1,1);

        return matchRepository.findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName,startDate,endDate,teamName,startDate,endDate);
    }
}
