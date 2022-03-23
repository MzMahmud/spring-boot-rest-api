package com.moazmahmud.spring_boot_rest_api.notification;

import com.moazmahmud.spring_boot_rest_api.common.PageResponse;
import com.moazmahmud.spring_boot_rest_api.common.PaginationUtil;
import com.moazmahmud.spring_boot_rest_api.common.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    public RestResponse getNotification(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "pageIndex", required = false) String pageIndexStr,
            @RequestParam(name = "pageSize", required = false) String pageSizeStr
    ) {
        PageResponse<Notification> userNotificationsByPage =
                notificationService.getUserNotificationsByPage(
                        userId,
                        PaginationUtil.getPageIndex(pageIndexStr),
                        PaginationUtil.getPageSize(pageSizeStr)
                );
        return RestResponse
                .builder()
                .success(true)
                .time(LocalDateTime.now())
                .payload(userNotificationsByPage)
                .build();
    }
}
