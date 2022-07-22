package com.practice.vpalagin.project.conroller;

import com.practice.vpalagin.project.dto.card.CardDto;
import com.practice.vpalagin.project.security.userDetails.JwtUser;
import com.practice.vpalagin.project.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/card")
public class CardController {
    private final CardService cardService;

    @PostMapping()
    public ResponseEntity<?> saveCard(@RequestBody CardDto cardDto, @AuthenticationPrincipal JwtUser jwtUser) {
        cardService.save(cardDto, jwtUser);
        return ResponseEntity.created(URI.create("/cards")).build();
    }

    @GetMapping()
    public ResponseEntity<?> get(@AuthenticationPrincipal JwtUser jwtUser) {
        return ResponseEntity.ok(cardService.get(jwtUser));
    }

    @GetMapping("/my-cards")
    public ResponseEntity<?> getMy(@AuthenticationPrincipal JwtUser jwtUser) {
        return ResponseEntity.ok(cardService.getMy(jwtUser));
    }

    @GetMapping("/other-cards")
    public ResponseEntity<?> getOther(@AuthenticationPrincipal JwtUser jwtUser) {
        return ResponseEntity.ok(cardService.getOther(jwtUser));
    }
}
