package cn.zhangtianxiao.jf_example._interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import javax.servlet.http.HttpSession;

/**
 * create by 张天笑, 2017/10/3 23:50
 * 定义一个全局拦截器, 这个地方Jf要比spring好理解多了
 */
public class Globalinterceptor implements Interceptor {
    public void intercept(Invocation invocation) {
        //当前被拦截的controller对象
        Controller controller = invocation.getController();
        //获取当前session对象
        HttpSession session = controller.getSession();

        System.out.println("全局拦截器执行了!~");

        //调用invoke()方法后才会继续执行对应的method,
        if ("".equals(" ".trim())) {
            invocation.invoke();
        } else {
            controller.redirect("https://www.baidu.com");
        }
        //invocation.get * ...
    }
}
