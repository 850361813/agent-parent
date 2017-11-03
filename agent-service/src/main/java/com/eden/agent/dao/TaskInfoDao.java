package com.eden.agent.dao;

import com.eden.agent.domain.TaskInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskInfoDao {
    List<TaskInfo> selectTaskInfo();
    TaskInfo selectByTaskName(@Param("taskName") String taskName);
    void updateTaskInfo(@Param("taskStatus") int taskStatus, @Param("taskDisp") String taskDisp, @Param("taskName") String taskName);
}
