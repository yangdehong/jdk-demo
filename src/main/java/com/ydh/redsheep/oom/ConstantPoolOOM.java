package com.ydh.redsheep.oom;

import java.util.HashSet;
import java.util.Set;

/**
* 运行时常量池OOM
* @author : yangdehong
* @date : 2021/2/24 14:02
*/
public class ConstantPoolOOM {

    public static void main(String[] args) {
        // 使用Set保持着常量池引用， 避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf("分为发好久诶入户柜一而后个IE让我孤儿inGVIEor那个人情全部任务那边改覅金额琴女IE日前被虐 而积分狗日被公认为一花覅万能福我我确认你发起人"+i++).intern());
        }
    }

}
