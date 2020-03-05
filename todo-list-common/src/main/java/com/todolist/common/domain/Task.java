package com.todolist.common.domain;

import com.todolist.common.convert.TaskStatusConverter;
import com.todolist.common.domain.enums.TaskStatus;
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

    @Column(name = "TITLE")
    private String title;

    @Column(name = "MEMO")
    private String memo;

    @Column(name = "STATUS_CD")
    @Enumerated(EnumType.STRING)
    @Convert(converter = TaskStatusConverter.class)
    private TaskStatus status;

    @Column(name = "ORD_NO")
    private Long orderNo;

    @Column(name = "DEL_YN")
    private String deleteYn;

    @Column(name = "START_DTTM")
    private LocalDateTime startDate;

    @Column(name = "END_DTTM")
    private LocalDateTime endDate;

    @Column(name = "CREATE_DTTM", nullable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DTTM", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Builder
    public Task(String title, String memo, Long orderNo, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.memo = memo;
        this.orderNo = orderNo;
        this.status = TaskStatus.READY;
        this.deleteYn = "N";
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        ;
    }

    public Task modifyUpdateDate() {
        this.updateDate = LocalDateTime.now();
        return this;
    }

    public Task modifyTitle(String title) {
        this.title = title;
        return this;
    }

    public Task modifyMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public Task modifyOrderNo(Long orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Task modifyStatus(TaskStatus taskStatus) {
        this.status = taskStatus;
        return this;
    }

    public Task modifyPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }

    public Task delete() {
        this.deleteYn = "Y";
        return this;
    }

}
