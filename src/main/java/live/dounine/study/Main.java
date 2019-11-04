package live.dounine.study;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * @project ApacheCommonsTest
 * @class Main
 * @author douNine
 * @date 2019/11/2 17:23
 * @description TODO
 */

public class Main {
    public static void sendTime(int n) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置日期格式
        EmailManager emailManager = new EmailManager();
        for (int i = 0; i < n; i++) {
            String time = df.format(new Date()); // new Date()为获取当前系统时间
            emailManager.send("949835972@qq.com", "949835972@qq.com", "Time", time);
        }
    }

    public static void main(String [] agrs) throws Exception {
        sendTime(1);
    }
}
