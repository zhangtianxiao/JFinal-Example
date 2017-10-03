package cn.zhangtianxiao.jf_example._model;

import cn.zhangtianxiao.jf_example.App;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

/**
 * create by 张天笑, 2017/10/4 0:57
 */
public class _Generator {

    public static DataSource getDataSource() {
        DruidPlugin druidPlugin = App.getDruidPlugin();
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    public static void main(String[] args) {
        // base model 所使用的包名
        String baseModelPackageName = "cn.zhangtianxiao.jf_example._model.base";
        // base model 文件保存路径
        String baseModelOutputDir = PathKit.getWebRootPath()
                + "/src/main/java/cn/zhangtianxiao/jf_example/_model/base";
        System.out.println("输出路径：" + baseModelOutputDir);
        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "cn.zhangtianxiao.jf_example._model";
        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = baseModelOutputDir + "/..";
        // 创建生成器
        Generator gen = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
        // 设置数据库方言
        gen.setDialect(new PostgreSqlDialect());
        // 设置是否在 Model 中生成 dao 对象
        gen.setGenerateDaoInModel(true);
        // 设置是否生成字典文件
        gen.setGenerateDataDictionary(false);
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        gen.setRemovedTableNamePrefixes("t_");
        //允许model setter返回this,方便链式调用, 但JavaBean的规范当中setter是没有返回值的, 在某些框架或工具当中可能会忽略有返回值的setter
        gen.setGenerateChainSetter(true);
        // 生成
        gen.generate();
    }
}

