package com.moazmahmud.spring_boot_rest_api.notification;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(generator = "seq_notification")
    @SequenceGenerator(name = "seq_notification", sequenceName = "seq_notification", allocationSize = 1)
    private Long id;

    @Column(name = "from_user_id")
    private Long fromUserId;

    @Column(name = "to_user_id")
    private Long toUserId;

    @Column(name = "text")
    private String text;

    @Column(name = "url")
    private String url;

    @Column(name = "is_seen")
    private Boolean isSeen;

    @Column(name = "seen_time")
    private LocalDateTime seenTime;
}
