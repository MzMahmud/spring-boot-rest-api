package com.moazmahmud.spring_boot_rest_api.cricket_team;

import com.moazmahmud.spring_boot_rest_api.common.BaseRestController;
import com.moazmahmud.spring_boot_rest_api.common.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/cricket-teams")
public class CricketTeamController extends BaseRestController {

    private final CricketTeamService cricketTeamService;

    @GetMapping
    public RestResponse getCricketTeams() {
        List<CricketTeamResponse> cricketTeams = cricketTeamService.getCricketTeams();
        return RestResponse
                .builder()
                .success(true)
                .time(LocalDateTime.now())
                .payload(cricketTeams)
                .build();
    }

    @PostMapping
    public ResponseEntity<Void> addCricketTeam(
            @RequestBody CricketTeamAddRequest addRequest
    ) throws URISyntaxException {
        CricketTeam cricketTeam = cricketTeamService.addCricketTeam(addRequest);
        String createdUrl = "/api/v1/cricket-teams/" + cricketTeam.getId();
        return ResponseEntity
                .created(new URI(createdUrl))
                .build();
    }

    @GetMapping("/{id}")
    public RestResponse getCricketTeam(
            @PathVariable("id") Long id
    ) {
        CricketTeamResponse cricketTeamResponse = cricketTeamService.getCricketTeam(id);
        return RestResponse
                .builder()
                .success(true)
                .time(LocalDateTime.now())
                .payload(cricketTeamResponse)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCricketTeam(
            @PathVariable("id") Long id,
            @RequestBody CricketTeamAddRequest addRequest
    ) {
        cricketTeamService.updateCricketTeam(id, addRequest);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCricketTeam(
            @PathVariable("id") Long id
    ) {
        cricketTeamService.deleteCricketTeam(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{cricketTeamId}/cricket-players")
    public RestResponse getCricketPlayers(
            @PathVariable("cricketTeamId") Long cricketTeamId
    ) {
        CricketTeamResponseWithPlayers responseWithPlayers =
                cricketTeamService.getCricketPlayers(cricketTeamId);
        return RestResponse
                .builder()
                .success(true)
                .time(LocalDateTime.now())
                .payload(responseWithPlayers)
                .build();
    }
}
