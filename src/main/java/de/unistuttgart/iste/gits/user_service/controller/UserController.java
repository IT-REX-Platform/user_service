package de.unistuttgart.iste.gits.user_service.controller;

import de.unistuttgart.iste.gits.common.user_handling.LoggedInUser;
import de.unistuttgart.iste.gits.generated.dto.PublicUserInfo;
import de.unistuttgart.iste.gits.generated.dto.UserInfo;
import de.unistuttgart.iste.gits.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @QueryMapping
    List<PublicUserInfo> findPublicUserInfos(@Argument List<UUID> ids) {
        return userService.findPublicUserInfos(ids);
    }

    @QueryMapping
    public UserInfo currentUserInfo(@ContextValue LoggedInUser currentUser) {
        log.info("Current user: " + currentUser.getId());
        return userService.getUserInfo(currentUser.getId());
    }

    @QueryMapping
    public List<UserInfo> findUserInfos(@Argument List<UUID> ids) {
        return userService.findUserInfos(ids);
    }
}
