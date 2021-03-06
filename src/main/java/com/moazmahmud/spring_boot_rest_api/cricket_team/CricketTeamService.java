package com.moazmahmud.spring_boot_rest_api.cricket_team;

import com.moazmahmud.spring_boot_rest_api.common.NotFoundException;
import com.moazmahmud.spring_boot_rest_api.cricket_player.CricketPlayerService;
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
    private final CricketPlayerService cricketPlayerService;

    private Optional<CricketTeam> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return cricketTeamRepository.findById(id);
    }

    @Transactional(readOnly = true)
    protected boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return cricketTeamRepository.existsById(id);
    }

    @Transactional
    protected Optional<CricketTeam> getEntityWithPlayersById(Long cricketTeamId) {
        if (cricketTeamId == null) {
            return Optional.empty();
        }
        return cricketTeamRepository.getEntityWithPlayersById(cricketTeamId);
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
        CricketTeam cricketTeam = setEntityFromAddRequest(new CricketTeam(), null, addRequest);
        return cricketTeamRepository.save(cricketTeam);
    }

    @Transactional
    public void updateCricketTeam(Long id, CricketTeamAddRequest addRequest) {
        CricketTeam cricketTeam = findById(id).orElseThrow(() -> new NotFoundException("No CricketTeam found with id=" + id));
        setEntityFromAddRequest(cricketTeam, cricketTeam.getId(), addRequest);
        cricketTeamRepository.save(cricketTeam);
    }

    private CricketTeamResponse mapEntityToResponse(CricketTeam cricketTeam) {
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
                .map(this::mapEntityToResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public CricketTeamResponse getCricketTeam(Long id) {
        return findById(id)
                .map(this::mapEntityToResponse)
                .orElseThrow(() -> new NotFoundException("No CricketTeam found with id=" + id));
    }

    public void deleteCricketTeam(Long id) {
        CricketTeam cricketTeam =
                findById(id).orElseThrow(() -> new NotFoundException("No CricketTeam found with id=" + id));
        cricketTeamRepository.delete(cricketTeam);
    }

    private CricketTeamResponseWithPlayers mapEntityToResponseWithPlayers(CricketTeam cricketTeam) {
        CricketTeamResponseWithPlayers responseWithPlayers = new CricketTeamResponseWithPlayers();
        responseWithPlayers.setId(cricketTeam.getId());
        responseWithPlayers.setName(cricketTeam.getName());
        responseWithPlayers.setNickName(cricketTeam.getNickName());
        responseWithPlayers.setCricketPlayerList(
                cricketTeam.getCricketPlayers()
                           .stream()
                           .map(cricketPlayerService::mapEntityToResponse)
                           .collect(Collectors.toUnmodifiableList())
        );
        return responseWithPlayers;
    }

    @Transactional(readOnly = true)
    public CricketTeamResponseWithPlayers getCricketPlayers(Long cricketTeamId) {
        return getEntityWithPlayersById(cricketTeamId)
                .map(this::mapEntityToResponseWithPlayers)
                .orElseThrow(() -> new NotFoundException("No CricketTeam found with id=" + cricketTeamId));
    }

    @Transactional
    public void addCricketPlayersToTeam(Long cricketTeamId, CricketTeamCricketPlayerAddRequest addRequest) {
        if (!existsById(cricketTeamId)) {
            throw new NotFoundException("No CricketTeam found with id=" + cricketTeamId);
        }
        cricketPlayerService.updateCricketPlayerTeam(
                addRequest.getCricketPlayerIds(),
                cricketTeamId
        );
    }
}
