package nl.han.oose.project.sb3.controller;

import nl.han.oose.project.sb3.dao.request.SignUpRequest;
import nl.han.oose.project.sb3.dao.request.SigninRequest;
import nl.han.oose.project.sb3.dao.response.JwtAuthenticationResponse;
import nl.han.oose.project.sb3.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController
{
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request)
    {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request)
    {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
