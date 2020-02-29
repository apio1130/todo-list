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
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskNo;
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

    @ManyToOne
    @JoinColumn(name = "group_no")
    private Groups groups;

    @Builder
    public Task(String title, String memo, Long orderNo, String cmplYn, String delYn, LocalDateTime startDttm,
                LocalDateTime endDttm, LocalDateTime rgstDttm, LocalDateTime modiDttm, Groups groups) {
        this.title = title;
        this.memo = memo;
        this.orderNo = orderNo;
        this.cmplYn = cmplYn;
        this.delYn = delYn;
        this.startDttm = startDttm;
        this.endDttm = endDttm;
        this.rgstDttm = rgstDttm;
        this.modiDttm = modiDttm;
        this.groups = groups;
    }

    public void setStartDttm(LocalDateTime startDttm) {
        this.startDttm = startDttm;
    }

    public void setEndDttm(LocalDateTime endDttm) {
        this.endDttm = endDttm;
    }

    public void setModiDttm(LocalDateTime modiDttm) {
        this.modiDttm = modiDttm;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public void update(Task task) {
        this.title = task.getTitle();
        this.memo = task.getMemo();
        this.orderNo = task.getOrderNo();
        this.cmplYn = task.getCmplYn();
        this.delYn = task.getDelYn();
        this.startDttm = task.getStartDttm();
        this.endDttm = task.getEndDttm();
        this.modiDttm = LocalDateTime.now();
        this.groups = task.getGroups();
    }

}
