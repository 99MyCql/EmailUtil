package live.dounine.study;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.IOException;

/***
 * @project EmailUtil
 * @class EmailUtil
 * @author douNine
 * @date 2019/11/2 17:40
 * @description 邮件工具类
 */
public class EmailUtil {
    private String host;
    private String port;
    private String username;
    private String passwd;

    /**
     * 构造函数，从配置文件中获取邮件服务器的相关信息
     * @throws Exception 当配置文件不能打开，或者配置文件信息有误时，抛出异常
     */
    public EmailUtil() throws Exception {
        // 加载配置文件
        PropertyUtil propertyUtil = null;
        try {
            propertyUtil = PropertyUtil.getSingleton();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("配置文件获取错误！");
        }

        // 获取邮件服务器的信息
        host = propertyUtil.getValue("email.host");
        port = propertyUtil.getValue("email.port");
        username = propertyUtil.getValue("email.username");
        passwd = propertyUtil.getValue("email.passwd");

        // 检查邮件服务器信息
        if (host.equals("") || port.equals("") || username.equals("") || passwd.equals("")) {
            throw new Exception("请检查配置文件config.properties！\n" +
                    "email.host、email.port、email.username、email.passwd 项都不能为空！");
        }
    }

    /**
     * 构造函数，设置邮件服务器相关信息
     * @param host 邮件服务器地址
     * @param port 邮件服务器端口
     * @param username 登录邮件服务器的用户名
     * @param passwd 登录邮件服务器的密码
     * @throws Exception 当存在为空项时，抛出异常
     */
    public EmailUtil(String host, String port, String username, String passwd) throws Exception {
        this.host = host;
        this.port = port;
        this.username = username;
        this.passwd = passwd;

        // 检查邮件服务器信息
        if (isEmpty(host, "邮件服务器地址不能为空！")
                || isEmpty(port, "邮件服务器端口不能为空！")
                || isEmpty(username, "用户名不能为空！")
                || isEmpty(passwd, "密码不能为空！")) {
            throw new Exception("存在为空项");
        }
    }

    /**
     * 检查字符串是否为空，若为空则输出错误信息
     * @param string 待测字符串
     * @param errMsg 错误信息
     * @return
     *      true: string is empty
     *      false: string is not empty
     */
    public boolean isEmpty(String string, String errMsg) {
        if (string.equals("")) {
            System.out.println(errMsg);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 发送邮件
     * @param fromEmail 发件人Email
     * @param toEmail 收件人Email
     * @param subject 主题
     * @param msg 内容
     * @return
     *      true: send successfully
     *      false: send fail
     */
    public boolean send(String fromEmail, String toEmail, String subject, String msg) {
        // 检查邮件信息
        if (isEmpty(fromEmail, "发件人Email不能为空！")
                || isEmpty(toEmail, "收件人人Email不能为空！")
                || isEmpty(subject, "邮件主题不能为空！")
                || isEmpty(msg, "邮件内容不能为空！")) {
            System.out.println("存在为空项！邮件发送失败！");
            return false;
        }

        try {
            Email email = new SimpleEmail(); // org.apache.commons.mail.Email对象

            // 设置邮件服务器信息
            email.setHostName(host);
            email.setSSLOnConnect(true);
            email.setSslSmtpPort(port);
            email.setAuthentication(username, passwd);

            // 设置额外信息
            email.setCharset("utf-8");
            email.setDebug(true); // 开启debug调试输出信息

            // 设置邮件的发件人、主题、内容、收件人
            email.setFrom(fromEmail);
            email.setSubject(subject);
            email.setMsg(msg);
            email.addTo(toEmail);

            // 发送邮件
            String msgId = email.send();

            System.out.println("邮件已发送：" + msgId);
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败！");
            return false;
        }
    }
}
