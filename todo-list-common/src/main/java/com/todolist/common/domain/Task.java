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
public class Task {

    @Id
    @Column(name = "TASK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String memo;
    @Column
    private Long orderNo;
    @Column
    private String cmplYn;
    @Column
    private String delYn;
    @Column
    private LocalDateTime startDttm;
    @Column
    private LocalDateTime endDttm;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime rgstDttm;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime modiDttm;


    @Builder
    public Task(String title, String memo, Long orderNo, LocalDateTime startDttm, LocalDateTime endDttm) {
        this.title = title;
        this.memo = memo;
        this.orderNo = orderNo;
        this.cmplYn = "N";
        this.delYn = "N";
        this.startDttm = startDttm;
        this.endDttm = endDttm;
        this.rgstDttm = registDateTime();
        this.modiDttm = updateDateTime();
    }


    private LocalDateTime registDateTime() {
        return LocalDateTime.now();
    }

    private LocalDateTime updateDateTime() {
        return LocalDateTime.now();
    }

    public void modifyTitle(String title) {
        this.title = title;
        this.modiDttm = updateDateTime();
    }

    public void modifyMemo(String memo) {
        this.memo = memo;
        this.modiDttm = updateDateTime();
    }

    public void modifyOrderNo(Long orderNo) {
        this.orderNo= orderNo;
        this.modiDttm = updateDateTime();
    }

}
