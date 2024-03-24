package nl.han.oose.project.sb3.service;

import nl.han.oose.project.sb3.dao.request.SignUpRequest;
import nl.han.oose.project.sb3.dao.request.SigninRequest;
import nl.han.oose.project.sb3.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
