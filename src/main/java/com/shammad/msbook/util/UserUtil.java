package com.shammad.msbook.util;

import com.shammad.msbook.domain.entity.UserEntity;
import com.shammad.msbook.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserUtil {

    private final UserRepository userRepository;

    public UserEntity getActiveUser() {
        return userRepository.findById(1L).get();
    }
}
