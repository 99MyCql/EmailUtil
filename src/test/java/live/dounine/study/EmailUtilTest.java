package live.dounine.study;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/***
 * @project EmailUtil
 * @class EmailUtilTest
 * @author douNine
 * @date 2019/11/3 15:04
 * @description 单元测试类
 */
public class EmailUtilTest {

    /**
     * 测试apache.commons.mail库
     */
    @Test
    public void test_mail() {
        Email email = new SimpleEmail();

        // 测试Email.setHostName()函数，是否成功设置
        String host = "stmp.qq.com";
        email.setHostName(host);
        assertEquals(host, email.getHostName());

        // 测试Email.setFrom()函数，是否能成功抛出异常
        try {
            email.setFrom("123456");
            fail();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试EmailManager.isEmpty()函数
     */
    @Test
    public void test_isEmpty() {
        EmailUtil emailUtil = null;
        try {
            emailUtil = new EmailUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(true, emailUtil.isEmpty("", "字符串为空！"));
        assertEquals(false, emailUtil.isEmpty("abcde", ""));
    }

    /**
     * 测试EmailManager.send()函数
     */
    @Test
    public void test_send() {
        EmailUtil emailUtil = null;
        try {
            emailUtil = new EmailUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(true, emailUtil.send("949835972@qq.com", "949835972@qq.com", "Test", "test"));
        assertEquals(false, emailUtil.send("949835972@qq.com", "", "Test", "test"));
        assertEquals(false, emailUtil.send("", "949835972@qq.com", "Test", "test"));
        assertEquals(false, emailUtil.send("949835972@qq.com", "949835972@qq.com", "", "test"));
        assertEquals(false, emailUtil.send("949835972@qq.com", "949835972@qq.com", "Test", ""));
    }

    /**
     * 采用集成测试的方法对send()函数进行测试
     */
    @Test
    public void test_send_byMock() {
        EmailUtil emailUtil = null;
        try {
            emailUtil = new EmailUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fromEmail = "949835972@qq.com";
        String toEmail = "949835972@qq.com";
        String subject = "Test";
        String msg = "test";

        // 未设置桩模块
        assertEquals(true, emailUtil.send(fromEmail, toEmail, subject, msg));

        // 对isEmpty()函数进行桩模块设置
        // EmailUtil emailManager_mock = mock(EmailUtil.class); // 创建mock对象
        EmailUtil emailUtil_spy = spy(emailUtil); // 创建spy对象，mock对象不能调用EmailManager的构造函数
        when(emailUtil_spy.isEmpty(fromEmail, "发件人Email不能为空！")).thenReturn(false); // 设置预期返回结果
        when(emailUtil_spy.isEmpty(toEmail, "收件人人Email不能为空！")).thenReturn(false);
        when(emailUtil_spy.isEmpty(subject, "邮件主题不能为空！")).thenReturn(false);
        when(emailUtil_spy.isEmpty(msg, "邮件内容不能为空！")).thenReturn(false);

        // 设置了桩模块之后
        assertEquals(true, emailUtil_spy.send(fromEmail, toEmail, subject, msg));
    }
}
