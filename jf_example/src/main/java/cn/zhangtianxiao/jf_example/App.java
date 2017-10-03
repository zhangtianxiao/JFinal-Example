package cn.zhangtianxiao.jf_example;

import cn.zhangtianxiao.jf_example._interceptor.Globalinterceptor;
import cn.zhangtianxiao.jf_example._model._MappingKit;
import cn.zhangtianxiao.jf_example._router.CommonRouter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

import java.sql.Connection;

/**
 * create by 张天笑, 2017/10/4 0:28
 */
public class App extends JFinalConfig {

    private static Prop prop = loadConfig();

    /*
    * 启动项目
    * 在IDEA下debug模式启动, 自带热加载,build一下就好,
    * Eclipse, n久没用, 不清楚
    * */
    public static void main(String[] args) {
        /*
        * src/main/webapp: 指定web资源目录,也可以像springboot那样放到resources目录下
        * 目前JF还没有去掉xml的配置,放哪都一样了.
        * 80: 端口
        * /example: 项目访问根
        * */
        JFinal.start("src/main/web", 80, "/example");
        //JFinal.start("src/main/webapp", 80, "/",5); //Eclipse下的启动方式
    }

    /*
    * 统一配置路由, 方便分模块开发
    * */
    public void configRoute(Routes routes) {
        routes.add(new CommonRouter());
    }

    public void configConstant(Constants constants) {
        constants.setDevMode(prop.getBoolean("devMode", false));        //设置开发模式为true, 能在控制台看到很多有用的信息
       // constants.setJsonFactory(MixedJsonFactory.me());
        constants.setViewExtension(".html");
    }

    /*
    * 模板第一次被调用后会被缓存到内存以提升效率.
    * 设置开发模式为true支持模板热加载
    * */
    public void configEngine(Engine engine) {
        engine.setDevMode(prop.getBoolean("engineDevMode", false));
    }

    /*
    * 创建DruidPlugin,Jfinal中RedisPlugin, EhcachePlugin,等都是以插件方式存在,不限于在Jfinal环境中使用
    * */
    public static DruidPlugin getDruidPlugin() {
        return new DruidPlugin(prop.get("url"), prop.get("username"), prop.get("password"));
    }

    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = getDruidPlugin();
        WallFilter wallFilter = new WallFilter();   // 加强数据库安全
        wallFilter.setDbType("postgresql");
        druidPlugin.addFilter(wallFilter);
        plugins.add(druidPlugin);

        //创建ActiveRecordPlugin, 跟数据库打交道就看它了
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setDialect(new PostgreSqlDialect());
        arp.setTransactionLevel(Connection.TRANSACTION_READ_COMMITTED);
        _MappingKit.mapping(arp);
        plugins.add(arp);
        if (prop.getBoolean("devMode", false)) {
            arp.setShowSql(true);
        }
        arp.setBaseSqlTemplatePath(PathKit.getRootClassPath() + "/sql");
        arp.addSqlTemplate("all_sqls.sql");
    }

    /*
    * 此处设置的拦截器为全局拦截器
    * */
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new SessionInViewInterceptor());   //可在模板中使用#(session.xx.yy)
        interceptors.add(new Globalinterceptor());
    }

    public void configHandler(Handlers handlers) {

    }

    public void afterJFinalStart() {
        System.out.println("-----  启动完毕 -----");
    }

    private static Prop loadConfig() {
        // 优先加载生产环境配置文件
        return PropKit.use("dev.properties");
    }
}
