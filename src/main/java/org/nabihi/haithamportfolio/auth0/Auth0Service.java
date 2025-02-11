package org.nabihi.haithamportfolio.auth0;


import org.nabihi.haithamportfolio.UserSubdomain.PresentationLayer.UserResponseModel;
import reactor.core.publisher.Mono;

public interface Auth0Service {

    Mono<UserResponseModel> getUserById(String auth0UserId);
    Mono<Void> assignRoleToUser(String auth0UserId, String roleName);
}
