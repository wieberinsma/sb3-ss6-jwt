package nl.han.oose.project.sb3.service;

import nl.han.oose.project.sb3.domain.request.SignUpRequest;
import nl.han.oose.project.sb3.domain.request.SigninRequest;
import nl.han.oose.project.sb3.domain.response.JwtAuthenticationResponse;

public interface AuthenticationService
{
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
