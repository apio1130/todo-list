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
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subTaskNo;
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
    @JoinColumn(name = "task_no")
    private Task task;

    @Builder
    public SubTask(String title, Long orderNo, String cmplYn, String delYn, LocalDateTime rgstDttm,
                   LocalDateTime modiDttm, Task task) {
        this.title = title;
        this.orderNo = orderNo;
        this.cmplYn = cmplYn;
        this.delYn = delYn;
        this.rgstDttm = rgstDttm;
        this.modiDttm = modiDttm;
        this.task = task;
    }

    public void setRgstDttm(LocalDateTime rgstDttm) {
        this.rgstDttm = rgstDttm;
    }

    public void setModiDttm(LocalDateTime modiDttm) {
        this.modiDttm = modiDttm;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void update(SubTask subTask) {
        this.title = subTask.getTitle();
        this.orderNo = subTask.getOrderNo();
        this.cmplYn = subTask.getCmplYn();
        this.delYn = subTask.getDelYn();
        this.modiDttm = LocalDateTime.now();
    }

}
