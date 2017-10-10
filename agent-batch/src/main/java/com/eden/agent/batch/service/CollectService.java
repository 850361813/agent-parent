package com.eden.agent.batch.service;

public class CollectService {
    public static void main(String[] args) throws Exception {
        Process proc = Runtime.getRuntime().exec("python  E:\\workspace\\crawer\\youtube_crawer_task.py");
        proc.waitFor();

//        PythonInterpreter interpreter = new PythonInterpreter();
//        InputStream filepy = new FileInputStream("E:\\workspace\\crawer\\youtube_crawer_task.py");
//        interpreter.execfile(filepy);  ///执行python py文件
//        filepy.close();
    }
}
