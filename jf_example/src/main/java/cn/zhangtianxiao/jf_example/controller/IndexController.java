package cn.zhangtianxiao.jf_example.controller;

import cn.zhangtianxiao.jf_example._model.User;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * create by 张天笑, 2017/10/3 23:32
 * <p>
 * 定义一个controller, 类比springmvc中的@Controller注解
 *
 * @Before 是Jf的aop实现, 在进入请求前执行其中的逻辑
 */
//@Before(Globalinterceptor.class)
public class IndexController extends Controller {

    /*
    * 如何找到index.html, 见 @CommonRouter.java
    * */
    public void index() {
        User admin = new User().setNickName("剑非道");
        setSessionAttr("user", admin);
        setAttr("msg", "悟来时见江海古，苍崖行遍谒玄门；向道偶题人间世，一笛一剑一昆仑");
        List<Record> userList = Db.find("select id,nick_name, to_char(create_time,'yyyy-MM-dd') as create_time from t_user order by create_time");
        setAttr("userList", userList);
        renderTemplate("index.html");
    }

    /*
    * 类比springmvc中RequestMapping注解
    * 使用@ActionKey自定义URI
    * */
    @ActionKey("/json")
    public void getJSON() {
        /*
        *  SqlPara sqlPara = Db.getSqlPara("user.pageList", new java.util.Date());
        *  这么写就会报错, 能像model.中一样,同意为java.util.Date()吗.
        * */

        SqlPara sqlPara = Db.getSqlPara("user.pageList", new java.sql.Date(System.currentTimeMillis()));
        System.out.println(sqlPara.getSql());
        Page<Record> userList = Db.paginate(10, 1, sqlPara);
        renderJson(userList);
    }
}
