package com.eden.agent.service;

import org.springframework.stereotype.Service;

/**
 * Create by zhaoxianghui on 2017/10/12.
 */
@Service
public class CollectService {
    public static void main(String[] args) throws Exception {
        Process proc = Runtime.getRuntime().exec("python  "
                                                         + "/Users/baidu/IdeaProjects/agent-parent/agent-batch/src/main/script/crawer-youtube/youtube_crawer_task.py");
        proc.waitFor();
    }
}
