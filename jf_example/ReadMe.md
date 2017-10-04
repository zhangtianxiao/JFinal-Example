
## 半个小时, 让你见识Jfinal的可爱之处 ##
---

***即便你没有读过jfinal-3.x-manual.pdf***

***项目运行前提要有Java和maven环境, 以及postgres数据库***

>* _Generator.java用来生成与table对应的实体类
>* _MappingKit.java用来统一注入model与table的映射
>* App.java, 项目配置类
>* 跟着App.java的注释, 你会看懂怎么用
JFianl官方Q群: 222478625
Java开发交流群:196187884, 258298887
    欢迎加入, 一起进步
    

### 对比JFinal和传统SSM,SSH ###
---
**控制器:**

>* JF简洁大方,函数名和@ActionKey都可以作为请求uri.
>*Api灵活,setAttr,getSessionAttr随手可用,不像springmvc非得写个HttpServletRequest的形参.
>* spring-mvc依赖了hibernate-validate,完美的参数校验机制.
>* 前台参数到控制器的形参注入,JF没有,但JF有controller.getModel().
>* @CookieValue,@SessionValue这些注解我宁可不用.搞得函数声明臭长难看.
>* ***JF vs SpringMvc  ->  SpringMvc略胜一筹***
    

**模板引擎**
>* JF-Template-Engine,自带 #(),    #for(),     #if() #elseif() #end ,#define,#set6个指令
>* 不是<% %>的脚本式, 也不是<C:if >这样的标签式,而是简洁干练的指令式
>* 两个巴掌都数的过来, 看一遍就记住
>* 还有for.count,for.index这样体贴的自带属性. jsp怎么玩?
>* 通过shareObjec, 可以把任何对象的任意方法交给模板. 不仅仅是view模板, 还有sql模板!
>*  ***JF vs Everything  ->  JF完胜***


**拦截器**
>* JF只提供了@Before,接受一个拦截器的class对象.
>* Spring提供了前置通知, 后置通知, 环绕通知, 最终通知,异常通知,好像是这五个吧?
>* 但也增加了切面,切入点,连接点,切点表达式等让人头疼的概念.
>* 有需求了就是spring好使 , 用不到, 一个前置通知够了
>* ***JF vs SpringMvc  ->  spring完胜***

**数据库访问**
> Hibernate,Mybatis, 咳咳, 我都没学好,因为太烦了...
当时学hibernate超级讨厌的配置有Hql,Qbc,Sql三种查询,cascade,inverse等等配置...反正学完就忘

> mybatis这东西别人都说好...我前后学这玩意儿四次,看教程看文档
每次都跪到一半, 我特么写个sql还要写xml标签? 什么#{}和${}

> 哦对了, 还有什么session工厂,二级缓存? JF的Db_Record没有这些无聊的概念.
数据库缓存只要一层就够了, 缓存规则由你自己定义, 而不该由什么狗屁框架横插一脚.

> Db_Record跟进源码就能看到, 只是对jdbc的简易封装,效率绝壁不差.
Model和Record存储数据依赖内部的一个map, 天生支持多表联查,天然支持别名
还记得以前用hibernate除了跟table对应的Bean,还得为联合查询准备pojo...

> 通过sqlTemplateEngine可以模板化管理sql
亦可使用#if , #for 等方式拼接sql

> Db.find,query,dao.find,query等. 简单易学易配置易理解
> ***JF vs Everything  ->  JF完胜***

                   
---
***我做事儿喜欢省事儿,***
***我的目的是简化开发,不是学习框架,更不是学习底层原理,设计模式***
***因为恶心xml走向JFinal,***
***因为恶心mybaits走向JF-Db_Record, 因为简洁走向JF_Template***
***所以我手头项目的技术选型是以SpringBoot为基,整合了JF-Db_Record和JF-EngineTemplate***
***嗯, 在3.2以前就是啦~***

**复杂要有复杂的意义,复杂要有复杂的必要.**
**化繁为简才是框架该干的事儿**
**spring比之jfinal也只有两地优势**
**一是一大堆反射和工厂造就的单例,创建对象的资源消耗有多大呢?[参考下这篇文章吧][1]**
**二是生态,因为它单例所以效率高, 所以要把对象的创建权交给它, 然后它的生态越来越大, 各种流行框架都有和spring整合的案例, 嗯, 没毛病**

作者 [@张天笑][2] 
2017 年 10 月 04 日


  [1]: https://www.oschina.net/question/197668_221395
  [2]: https://github.com/zhangtianxiao/