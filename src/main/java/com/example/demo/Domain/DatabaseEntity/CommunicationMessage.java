package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "CommunicationMessages", schema = "dbo")
public class CommunicationMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SenderId", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CommunicationId", nullable = false)
    private UserCommunication communication;

    @Nationalized
    @Column(name = "Message", nullable = false, length = 250)
    private String message;

}