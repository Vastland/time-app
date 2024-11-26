package org.timeapp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Times {

    @Id
    public String id;

    private String time;

    private String createdAt;
}
