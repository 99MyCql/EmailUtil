# EmailUtil

## 0. 介绍

`EmailManager`: a util of sending email
`PropertyManager`: a util of reading .properties

## 1. EmailManager使用

### 1.1. 实例化

实例化`EmailManager`类，有两种方法：

#### 1.1.1. 方法一

实例化时，通过构造函数传入邮件服务器的信息：

```java
/**
 * 构造函数，设置邮件服务器相关信息
 * @param host 邮件服务器地址
 * @param port 邮件服务器端口
 * @param username 登录邮件服务器的用户名
 * @param passwd 登录邮件服务器的密码
 * @throws Exception 当存在为空项时，抛出异常
 */
public EmailManager(String host, String port, String username, String passwd) throws Exception {}
```

#### 1.1.2. 方法二

通过读取`config.properties`配置文件中邮件服务器的信息，进行实例化

```java
/**
 * 构造函数，从配置文件中获取邮件服务器的相关信息
 * @throws Exception 当配置文件不能打开，或者配置文件信息有误时，抛出异常
 */
public EmailManager() throws Exception {}
```

`config.properties`配置文件在`src\main\resource`目录下，需用户自行创建，其中需填写的信息示例如下：

```properties
# qq邮件服务器
email.host = smtp.qq.com
email.port = 465
email.username = xxxxxxxx@qq.com
email.passwd = xxxxxxxxxxxxxxx # 16位授权码
```

### 1.2. 发送邮件

调用`send()`方法即可，该方法调用规约如下：

```java
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
public boolean send(String fromEmail, String toEmail, String subject, String msg) {}
```

## 2. 测试

在`EmailManagerTest`中：

- 对`apache.commons.mail`库中的`SimpleEmail.setFrom()`函数进行了单元测试

- 对`EmailManager.isEmpty()`函数进行了单元测试

- 对`EmailManager.send()`函数进行了单元测试

- 对`EmailManager.send()`函数进行了集成测试
