package com.moazmahmud.spring_boot_rest_api.notification;

import com.moazmahmud.spring_boot_rest_api.common.PageResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@AllArgsConstructor
public class NotificationService {
    private static final Sort DEFAULT_SORT = Sort.by(List.of(
            new Sort.Order(ASC, "isSeen"),
            new Sort.Order(DESC, "seenTime")
    ));

    private final NotificationRepository notificationRepository;

    public PageResponse<Notification> getUserNotificationsByPage(Long userId, Integer pageIndex, Integer pageSize) {
        Page<Notification> notificationPage =
                notificationRepository.findByToUserId(userId, PageRequest.of(pageIndex, pageSize, DEFAULT_SORT));
        return PageResponse.from(notificationPage);
    }
}
