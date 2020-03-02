package com.todolist.common.repository;

import com.todolist.common.domain.Groups;
import com.todolist.common.domain.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
class GroupsRepositoryTest {

    private GroupsRepository groupsRepository;

    private TaskRepository taskRepository;

    @Autowired
    public GroupsRepositoryTest(GroupsRepository groupsRepository, TaskRepository taskRepository) {
        this.groupsRepository = groupsRepository;
        this.taskRepository = taskRepository;
    }

    @BeforeEach
    void setUp() {
        Groups group = groupsRepository.save(Groups.builder().title("할일").delYn("N").build());
        IntStream.range(1, 11).forEach(i -> taskRepository.save(Task.builder().title("To-do #" + i)
                .cmplYn("N").delYn("N").groups(group).build()));
    }

    @DisplayName("Groups 조회 테스트")
    @Test
    void select() {
        Optional<Groups> groups = groupsRepository.findById(1L);
        groups.ifPresent(g -> {
            log.debug("{}", g.toString());
            assertThat(g.getTitle(), is("할일"));
        });
    }

    @DisplayName("Groups 등록 테스트")
    @Test
    void save() {
        Groups groups = groupsRepository.save(Groups.builder().title("중요한 일").delYn("N").build());
        List<Groups> groupsList = groupsRepository.findAll();
        long count = groupsList.stream().filter(g -> groups.getTitle().equals(g.getTitle())).count();
        assertThat(count, not(0));
    }

    @DisplayName("Groups 수정 테스트")
    @Test
    void update() {
        Groups findGroups = groupsRepository.findById(1L).orElse(new Groups());
        log.debug("find group info : {}", findGroups);
        findGroups.update(Groups.builder()
                .title("오늘 할일")
                .delYn(findGroups.getDelYn())
                .build());
        groupsRepository.save(findGroups);

        Groups updatedGroups = groupsRepository.findById(findGroups.getGroupNo()).orElse(new Groups());
        log.debug("updated Groups info : {}", updatedGroups);
        assertThat(findGroups.getTitle(), is("오늘 할일"));
    }

}
