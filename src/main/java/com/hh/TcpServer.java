package com.hh;

import com.hh.db.TacticsDB;
import com.hh.entry.SysCache;
import com.hh.entry.TacticsTCP;
import com.hh.jdbc.JdbcUtil;
import com.hh.thread.TcpRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * tcp服务
 *
 * @author zyj
 * @date 16:05 2019/5/12
 */
public class TcpServer {

    private static Logger log = LoggerFactory.getLogger(TcpServer.class);
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        //查询 客户端记录 jdbc

        //启动监听 代码

        //循环

        //读取 客户端 信息
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"one");
        map.put(2,"two");
        map.put(3,"three");
        map.put(4,"four");
        map.put(5,"five");
        map.put(6,"six");
        map.put(7,"seven");
        map.put(8,"eight");
        map.put(5,"five");
        map.put(9,"nine");
        map.put(10,"ten");
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer, String> entry=it.next();
            int key=entry.getKey();
            if(key%2==1){
                System.out.println("delete this: "+key+" = "+key);
                //map.put(key, "奇数");   //ConcurrentModificationException
                //map.remove(key);      //ConcurrentModificationException
                it.remove();        //OK
            }
        }
        System.out.println("-------\n\t最终的map的元素遍历：");
        for(Map.Entry<Integer, String> entry:map.entrySet()){
            int k=entry.getKey();
            String v=entry.getValue();
            System.out.println(k+" = "+v);
        }
    }


}
