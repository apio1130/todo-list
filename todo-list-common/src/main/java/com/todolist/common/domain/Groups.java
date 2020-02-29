package com.todolist.common.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class Groups {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupNo;
    @Column
    private String title;
    @Column
    private Long orderNo;
    @Column
    private String delYn;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime rgstDttm;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime modiDttm;

    @Builder
    public Groups(String title, Long orderNo, String delYn, LocalDateTime rgstDttm, LocalDateTime modiDttm) {
        this.title = title;
        this.orderNo = orderNo;
        this.delYn = delYn;
        this.rgstDttm = rgstDttm;
        this.modiDttm = modiDttm;
    }

    public void update(Groups groups) {
        this.title = groups.getTitle();
        this.orderNo = groups.getOrderNo();
        this.delYn = groups.getDelYn();
        this.modiDttm = LocalDateTime.now();
    }

}
