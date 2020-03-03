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
public class SubTask {
    @Id
    @Column(name = "SUB_TASK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private Long orderNo;
    @Column
    private String cmplYn;
    @Column
    private String delYn;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime rgstDttm;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime modiDttm;

    @ManyToOne
    @JoinColumn(name = "TASK_ID")
    private Task task;

    private LocalDateTime registDateTime() {
        return LocalDateTime.now();
    }

    private LocalDateTime updateDateTime() {
        return LocalDateTime.now();
    }

    public void setModiDttm(LocalDateTime modiDttm) {
        this.modiDttm = modiDttm;
    }

    @Builder
    public SubTask(String title, Long orderNo, Task task) {
        this.title = title;
        this.orderNo = orderNo;
        this.cmplYn = "N";
        this.delYn = "N";
        this.task = task;
        this.rgstDttm = registDateTime();
        this.modiDttm = updateDateTime();
    }

    public void modifyTitle(String title) {
        this.title = title;
        this.modiDttm = updateDateTime();
    }

    public void modifyOrderNo(Long orderNo) {
        this.orderNo= orderNo;
        this.modiDttm = updateDateTime();
    }

}
