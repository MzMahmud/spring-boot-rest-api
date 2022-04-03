package com.moazmahmud.spring_boot_rest_api.cricket_player;

import com.moazmahmud.spring_boot_rest_api.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CricketPlayerService {

    private final CricketPlayerRepository cricketPlayerRepository;

    private Optional<CricketPlayer> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return cricketPlayerRepository.findById(id);
    }

    @Transactional
    public void updateCricketPlayerTeam(Collection<Long> ids, Long cricketTeamId) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        cricketPlayerRepository.updateCricketPlayerTeam(ids, cricketTeamId);
    }

    private CricketPlayer setEntityFromAddRequest(CricketPlayer cricketPlayer,
                                                  Long id,
                                                  CricketPlayerAddRequest addRequest) {
        cricketPlayer.setId(id);
        cricketPlayer.setName(addRequest.getName());
        cricketPlayer.setJerseyNumber(addRequest.getJerseyNumber());
        cricketPlayer.setCricketPlayerTypes(addRequest.getCricketPlayerTypes());
        return cricketPlayer;
    }

    @Transactional
    public CricketPlayer addCricketPlayer(CricketPlayerAddRequest addRequest) {
        CricketPlayer cricketPlayer = setEntityFromAddRequest(new CricketPlayer(), null, addRequest);
        return cricketPlayerRepository.save(cricketPlayer);
    }

    @Transactional
    public void updateCricketPlayer(Long id, CricketPlayerAddRequest addRequest) {
        CricketPlayer cricketPlayer = findById(id).orElseThrow(() -> new NotFoundException("No CricketPlayer found with id=" + id));
        setEntityFromAddRequest(cricketPlayer, cricketPlayer.getId(), addRequest);
        cricketPlayerRepository.save(cricketPlayer);
    }

    public CricketPlayerResponse mapEntityToResponse(CricketPlayer cricketPlayer) {
        CricketPlayerResponse cricketPlayerResponse = new CricketPlayerResponse();
        cricketPlayerResponse.setId(cricketPlayer.getId());
        cricketPlayerResponse.setName(cricketPlayer.getName());
        cricketPlayerResponse.setJerseyNumber(cricketPlayer.getJerseyNumber());
        cricketPlayerResponse.setCricketPlayerTypes(cricketPlayer.getCricketPlayerTypes());
        return cricketPlayerResponse;
    }

    @Transactional(readOnly = true)
    public List<CricketPlayerResponse> getCricketPlayers() {
        return cricketPlayerRepository
                .findAll()
                .stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public CricketPlayerResponse getCricketPlayer(Long id) {
        return findById(id)
                .map(this::mapEntityToResponse)
                .orElseThrow(() -> new NotFoundException("No CricketPlayer found with id=" + id));
    }

    public void deleteCricketPlayer(Long id) {
        CricketPlayer cricketPlayer = findById(id).orElseThrow(() -> new NotFoundException("No CricketPlayer found with id=" + id));
        cricketPlayerRepository.delete(cricketPlayer);
    }
}
