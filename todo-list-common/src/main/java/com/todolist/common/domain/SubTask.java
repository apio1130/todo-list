package com.todolist.common.domain;

import com.todolist.common.convert.SubTaskStatusConverter;
import com.todolist.common.domain.enums.SubTaskStatus;
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

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ORD_NO")
    private Long orderNo;

    @Column(name = "STATUS_CD")
    @Enumerated(EnumType.STRING)
    @Convert(converter = SubTaskStatusConverter.class)
    private SubTaskStatus status;

    @Column(name = "DEL_YN")
    private String deleteYn;

    @Column(name = "CREATE_DTTM", nullable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DTTM", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "TASK_ID")
    private Task task;

    @Builder
    public SubTask(String title, Long orderNo, Task task) {
        this.title = title;
        this.orderNo = orderNo;
        this.status = SubTaskStatus.READY;
        this.deleteYn = "N";
        this.task = task;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public void setStatus(SubTaskStatus status) {
        this.status = status;
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void updateDateTime() {
        this.updateDate = LocalDateTime.now();
    }
}
