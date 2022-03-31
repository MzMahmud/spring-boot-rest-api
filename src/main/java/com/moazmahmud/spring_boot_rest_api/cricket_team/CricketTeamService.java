package com.moazmahmud.spring_boot_rest_api.cricket_team;

import com.moazmahmud.spring_boot_rest_api.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CricketTeamService {

    private final CricketTeamRepository cricketTeamRepository;

    private Optional<CricketTeam> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return cricketTeamRepository.findById(id);
    }

    private CricketTeam setEntityFromAddRequest(CricketTeam cricketTeam,
                                                Long id,
                                                CricketTeamAddRequest addRequest) {
        cricketTeam.setId(id);
        cricketTeam.setName(addRequest.getName());
        cricketTeam.setNickName(addRequest.getNickName());
        return cricketTeam;
    }

    @Transactional
    public CricketTeam addCricketTeam(CricketTeamAddRequest addRequest) {
        CricketTeam cricketTeam = setEntityFromAddRequest(
                new CricketTeam(),
                null,
                addRequest
        );
        return cricketTeamRepository.save(cricketTeam);
    }

    @Transactional
    public CricketTeam updateCricketTeam(Long id, CricketTeamAddRequest addRequest) {
        CricketTeam cricketTeam =
                findById(id).orElseThrow(() -> new NotFoundException("No CricketTeam found with id=" + id));
        setEntityFromAddRequest(
                cricketTeam,
                cricketTeam.getId(),
                addRequest
        );
        return cricketTeamRepository.save(cricketTeam);
    }

    private CricketTeamResponse getResponseFromEntity(CricketTeam cricketTeam) {
        CricketTeamResponse cricketTeamResponse = new CricketTeamResponse();
        cricketTeamResponse.setId(cricketTeam.getId());
        cricketTeamResponse.setName(cricketTeam.getName());
        cricketTeamResponse.setNickName(cricketTeam.getNickName());
        return cricketTeamResponse;
    }

    @Transactional(readOnly = true)
    public List<CricketTeamResponse> getCricketTeams() {
        return cricketTeamRepository
                .findAll()
                .stream()
                .map(this::getResponseFromEntity)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public CricketTeamResponse getCricketTeam(Long id) {
        return findById(id)
                .map(this::getResponseFromEntity)
                .orElseThrow(() -> new NotFoundException("No CricketTeam found with id=" + id));
    }

    public void deleteCricketTeam(Long id) {
        CricketTeam cricketTeam =
                findById(id).orElseThrow(() -> new NotFoundException("No CricketTeam found with id=" + id));
        cricketTeamRepository.delete(cricketTeam);
    }
}
