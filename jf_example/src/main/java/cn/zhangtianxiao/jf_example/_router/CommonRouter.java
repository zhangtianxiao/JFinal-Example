package cn.zhangtianxiao.jf_example._router;

import cn.zhangtianxiao.jf_example.controller.IndexController;
import com.jfinal.config.Routes;

/**
 * create by 张天笑, 2017/10/3 23:36
 * 此路由下配置无需登录就能访问的资源
 */
public class CommonRouter extends Routes {
    public void config() {
        setBaseViewPath("/common");     //设置此路由的模板路径
        add("/", IndexController.class);
    }
}
