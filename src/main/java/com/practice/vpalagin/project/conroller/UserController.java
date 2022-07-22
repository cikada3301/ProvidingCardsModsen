package com.practice.vpalagin.project.conroller;

import com.practice.vpalagin.project.dto.user.UserAuthenticationDto;
import com.practice.vpalagin.project.dto.user.UserMutationDto;
import com.practice.vpalagin.project.security.JwtUtil;
import com.practice.vpalagin.project.security.userDetails.JwtUser;
import com.practice.vpalagin.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody UserAuthenticationDto userAuthenticationDto) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userAuthenticationDto.getUserName(),
                            userAuthenticationDto.getPassword()
                    )
            );
        } catch (BadCredentialsException badCredentialsException) {
            throw new UsernameNotFoundException("Incorrect email or password", badCredentialsException);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userAuthenticationDto.getUserName());

        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/password")
    public ResponseEntity<?> save(@Valid @RequestBody UserAuthenticationDto userAuthenticationDto,  @AuthenticationPrincipal JwtUser user) {
        userService.save(userAuthenticationDto, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/accountant")
    public ResponseEntity<?> saveAccountant(@RequestBody UserMutationDto userMutationDto, @AuthenticationPrincipal JwtUser jwtUser){
        userService.saveAccountant(userMutationDto, jwtUser);
        return ResponseEntity.created(URI.create("/accountants")).build();
    }

    @GetMapping("/accountants")
    public ResponseEntity<?> getAccountants(@AuthenticationPrincipal JwtUser jwtUser){
        return ResponseEntity.ok(userService.getAccountants(jwtUser));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> banUser(@PathVariable Long id, @AuthenticationPrincipal JwtUser jwtUser){
        userService.banUser(id, jwtUser);
        return ResponseEntity.ok().build();
    }
}
