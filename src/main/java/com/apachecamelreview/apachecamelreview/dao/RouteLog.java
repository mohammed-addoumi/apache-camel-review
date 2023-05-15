package com.apachecamelreview.apachecamelreview.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "route_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "route_id")
    private String routeId;

    @Column(name = "from_endpoint")
    private String fromEndpoint;

    @Column(name = "to_endpoint")
    private String toEndpoint;

    @Lob
    @Column(name = "body")
    private String body;

}
