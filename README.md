# Java-web-Exercise
Java web work

The work in university Java Web class.

## 第四章.会话管理:
会话（session）是客户与服务器之间的不间断的请求-相应序列。
HttpSession,request.getSession(),以Web容器为基础建立的对话,基于浏览器。
通过不同的浏览器建立不同的会话。
session.setAttribute("属性名",属性内容)设置session中的内容，get的时候要判断是否为空。
request的生命周期最短，只在请求那一瞬间存在与内存，session的生命周期，在预定的时限内一直都存在于内存，用户独享，context从服务器被开启开始，只有在服务器被关闭才会消失，且是所有用户共享。
setMaxInactiveInterval(int interval)设置最大超时时间，默认20分钟。

### 聊天室:
输入昵称登录

显示聊天页面

聊天内容区显示(定时刷新)：

xxxx 对 yyyy 说：笑笑笑笑笑笑笑笑笑

zzzz对 yyyy 说：士大夫精神的

xxxx对 所有人说： ssssss

操作区显示:

一个用户列表包括：所有人，张三，李四。。。。。

发送聊天内容的文本框：需要发言的内容

一个按钮：发送

按钮：退出聊天室

1、私聊信息只能自己和私聊对象看见

2、显示内容里可以显示：xxx进入聊天室，xxx推出聊天室

3、用户没有访问聊天室的会自动退出