package com.moazmahmud.spring_boot_rest_api.cricket_player;

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
@RequestMapping("/api/v1/cricket-players")
public class CricketPlayerController extends BaseRestController {

    private final CricketPlayerService cricketPlayerService;

    @GetMapping
    public RestResponse getCricketPlayers() {
        List<CricketPlayerResponse> cricketPlayers = cricketPlayerService.getCricketPlayers();
        return RestResponse
                .builder()
                .success(true)
                .time(LocalDateTime.now())
                .payload(cricketPlayers)
                .build();
    }

    @PostMapping
    public ResponseEntity<Void> addCricketPlayer(
            @RequestBody CricketPlayerAddRequest addRequest
    ) throws URISyntaxException {
        CricketPlayer cricketPlayer = cricketPlayerService.addCricketPlayer(addRequest);
        String createdUrl = "/api/v1/cricket-players/" + cricketPlayer.getId();
        return ResponseEntity
                .created(new URI(createdUrl))
                .build();
    }

    @GetMapping("/{id}")
    public RestResponse getCricketPlayer(
            @PathVariable("id") Long id
    ) {
        CricketPlayerResponse cricketPlayerResponse = cricketPlayerService.getCricketPlayer(id);
        return RestResponse
                .builder()
                .success(true)
                .time(LocalDateTime.now())
                .payload(cricketPlayerResponse)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCricketPlayer(
            @PathVariable("id") Long id,
            @RequestBody CricketPlayerAddRequest addRequest
    ) {
        cricketPlayerService.updateCricketPlayer(id, addRequest);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCricketPlayer(
            @PathVariable("id") Long id
    ) {
        cricketPlayerService.deleteCricketPlayer(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
