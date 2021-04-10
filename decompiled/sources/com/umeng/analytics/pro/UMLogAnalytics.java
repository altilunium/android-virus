package com.umeng.analytics.pro;

import com.umeng.commonsdk.debug.UMLogUtils;

/* renamed from: com.umeng.analytics.pro.i */
public class UMLogAnalytics {

    /* renamed from: A */
    public static final String f772A = "MobclickAgent.setSecret方法参数secretkey不能为null，也不能为空字符串。|secretkey参数必须是非空 字符串。";

    /* renamed from: B */
    public static final String f773B = "统计SDK常见问题索引贴 详见链接 http://developer.umeng.com/docs/66650/cate/66650";

    /* renamed from: C */
    public static final String f774C = ("MobclickAgent.onPageStart方法参数不能为null，也不能为空字符串。|参数viewName必须为非空字符串。详见链接 " + UMLogUtils.makeUrl("66975"));

    /* renamed from: D */
    public static final String f775D = ("MobclickAgent.onPageEnd方法参数不能为null，也不能为空字符串。|参数viewName必须为非空 字符串。详见链接 " + UMLogUtils.makeUrl("66975"));

    /* renamed from: E */
    public static final String f776E = ("对于页面@，onPageStart和onPageEnd调用对的参数不一致。|对于同一个页面，请先调用onPageStart，再调用onPageEnd。详见链接 " + UMLogUtils.makeUrl("66975"));

    /* renamed from: F */
    public static final String f777F = ("对于页面@，请确保先依序成对调用onPageStart，onPageEnd接口，再调用onPageStart接口对其它页面进行统计。|对于任意一个页面，必须依序成对调用onPageStart以及onPageEnd，不能有遗漏。详见链接 " + UMLogUtils.makeUrl("66975"));

    /* renamed from: G */
    public static final String f778G = ("对于页面@，请检查是否遗漏onPageStart接口调用。|对于任意一个页面，必须依序成对调用onPageStart以及onPageEnd，不能有遗漏。详见链接 " + UMLogUtils.makeUrl("66975"));

    /* renamed from: H */
    public static final String f779H = "检测到进入页面生命周期时尚未完成SDK初始化，请检查是否未在Application.onCreate函数中执行SDK初始化函数。";

    /* renamed from: I */
    public static final String f780I = ("当前发送策略为：启动时发送。详见链接 " + UMLogUtils.makeUrl("66976"));

    /* renamed from: J */
    public static final String f781J = ("当前发送策略为: 间隔发送。间隔时间为：@秒。详见链接 " + UMLogUtils.makeUrl("66976"));

    /* renamed from: K */
    public static final String f782K = ("当前发送策略为: 集成测试。但是SDK未切换到调试模式，所以后台设置未生效。|如想切换到集成测试发送策略，请调用UMConfigure.setLogEnabled(true)将SDK切换到调试模式。详见链接 " + UMLogUtils.makeUrl("66976"));

    /* renamed from: L */
    public static final String f783L = ("当前发送策略为：集成测试。详见链接 " + UMLogUtils.makeUrl("66976"));

    /* renamed from: M */
    public static final String f784M = ("当前发送策略为: 准实时发送。间隔时间为：@秒。详见链接 " + UMLogUtils.makeUrl("66976"));

    /* renamed from: N */
    public static final String f785N = "MobclickAgent.onDeepLinkReceived方法Context参数不能为null。|参数Context需要指定ApplicationContext值。";

    /* renamed from: O */
    public static final String f786O = "MobclickAgent.onDeepLinkReceived方法link参数不能为null，也不能为空字符串。|参数link必须为非空字符串。";

    /* renamed from: P */
    public static final String f787P = "MobclickAgent.onDeepLinkReceived方法link参数长度超过限制。|参数link长度不能超过1024字符。";

    /* renamed from: Q */
    public static final String f788Q = ("发送数据时发生java.net.UnknownHostException异常|友盟后端对设备端证书验证失败。请确保设备端没有运行抓包代理类程序。详见链接 " + UMLogUtils.makeUrl("66978"));

    /* renamed from: R */
    public static final String f789R = ("发送数据时发生javax.net.sslHandshakeException异常|导致友盟后端域名解析失败。请检查系统DNS服务器配置是否正确。详见链接 " + UMLogUtils.makeUrl("66978"));

    /* renamed from: S */
    public static final String f790S = ("track接口调用非法。|当前处于非DPLUS场景中，不能使用DPLUS相关接口，详见问题连接：" + UMLogUtils.makeUrl("67310"));

    /* renamed from: T */
    public static final String f791T = ("registerSuperProperty接口调用非法。|当前处于非DPLUS场景中，不能使用DPLUS相关接口，详见问题连接：" + UMLogUtils.makeUrl("67310"));

    /* renamed from: U */
    public static final String f792U = ("unregisterSuperProperty接口调用非法。|当前处于非DPLUS场景中，不能使用DPLUS相关接口，详见问题连接：" + UMLogUtils.makeUrl("67310"));

    /* renamed from: V */
    public static final String f793V = ("getSuperProperty接口调用非法。|当前处于非DPLUS场景中，不能使用DPLUS相关接口，详见问题连接: " + UMLogUtils.makeUrl("67310"));

    /* renamed from: W */
    public static final String f794W = ("getSuperProperties接口调用非法。|当前处于非DPLUS场景中，不能使用DPLUS相关接口，详见问题连接：" + UMLogUtils.makeUrl("67310"));

    /* renamed from: X */
    public static final String f795X = ("clearSuperProperties接口调用非法。|当前处于非DPLUS场景中，不能使用DPLUS相关接口，详见问题连接：" + UMLogUtils.makeUrl("67310"));

    /* renamed from: Y */
    public static final String f796Y = ("setFirstLaunchEvent接口调用非法。|当前处于非DPLUS场景中，不能使用DPLUS相关接口，详见问题连接：" + UMLogUtils.makeUrl("67310"));

    /* renamed from: Z */
    public static final String f797Z = ("registerPreProperties接口调用非法。|当前处于非DPLUS场景中，不能使用DPLUS相关接口，详见问题连接：" + UMLogUtils.makeUrl("67310"));

    /* renamed from: a */
    public static final String f798a = ("事件属性集合参数为空|onEvent接口必须传入非空的属性集合。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: aa */
    public static final String f799aa = ("unregisterPreProperty接口调用非法。|当前处于非DPLUS场景中，不能使用DPLUS相关接口，详见问题连接：" + UMLogUtils.makeUrl("67310"));

    /* renamed from: ab */
    public static final String f800ab = ("clearPreProperties接口调用非法。|当前处于非DPLUS场景中，不能使用DPLUS相关接口，详见问题连接：" + UMLogUtils.makeUrl("67310"));

    /* renamed from: ac */
    public static final String f801ac = ("getPreProperties接口调用非法。|当前处于非DPLUS场景中，不能使用DPLUS相关接口，详见问题连接：" + UMLogUtils.makeUrl("67310"));

    /* renamed from: ad */
    public static final String f802ad = ("eventName为空，请检查|eventName参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67311"));

    /* renamed from: ae */
    public static final String f803ae = ("请注意：map为空|track接口的参数说明，详见问题连接：" + UMLogUtils.makeUrl("67311"));

    /* renamed from: af */
    public static final String f804af = ("context参数为空｜context参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67312"));

    /* renamed from: ag */
    public static final String f805ag = ("propertyName参数或propertyValue参数为空｜propertyName、propertyValue参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67312"));

    /* renamed from: ah */
    public static final String f806ah = ("context参数为空|context参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67313"));

    /* renamed from: ai */
    public static final String f807ai = ("context参数为空|context参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67316"));

    /* renamed from: aj */
    public static final String f808aj = ("context参数为空|context参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67318"));

    /* renamed from: ak */
    public static final String f809ak = ("trackID参数为空|trackID参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67318"));

    /* renamed from: al */
    public static final String f810al = ("context参数为空|context参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67319"));

    /* renamed from: am */
    public static final String f811am = ("propertics参数为空|propertics参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67319"));

    /* renamed from: an */
    public static final String f812an = ("context参数为空|context参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67320"));

    /* renamed from: ao */
    public static final String f813ao = ("未匹配到您传入的property参数|property参数不能匹配，" + UMLogUtils.makeUrl("67320"));

    /* renamed from: ap */
    public static final String f814ap = ("context参数为空|context参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67321"));

    /* renamed from: aq */
    public static final String f815aq = ("context参数为空|context参数不能为空，详见问题连接：" + UMLogUtils.makeUrl("67322"));

    /* renamed from: ar */
    public static final String f816ar = "请在Application.onCreate函数中使用UMConfigure.preInit函数初始化友盟sdk";

    /* renamed from: b */
    public static final String f817b = ("事件ID和保留字冲突|onEvent接口传入的事件ID不能和保留字冲突。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: c */
    public static final String f818c = ("事件ID为null或者为空字符串|onEvent接口传入的事件ID不能为null，也不能为空字符串。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: d */
    public static final String f819d = ("事件属性集合map没有加入K-V值|事件属性集合参数map必须添加K-V值。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: e */
    public static final String f820e = ("事件属性集合map中key值和保留字冲突|事件属性集合map中key值不能和保留字冲突。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: f */
    public static final String f821f = ("事件ID为null或者长度超过限制|事件ID不能为null、空串，且长度不能超过128个字符。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: g */
    public static final String f822g = ("事件属性集合参数为空或者事件属性集合map没有加入K-V值|事件属性集合参数map必须添加K-V值。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: h */
    public static final String f823h = ("事件属性集合map中key非法|事件属性集合参数map中key不能为非法的。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: i */
    public static final String f824i = ("事件属性集合map中value为null|事件属性集合参数map中value不能为null。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: j */
    public static final String f825j = ("事件属性集合map中value长度超过限制|事件属性集合参数map中value如果为字符串时，其长度不能超过256个字符。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: k */
    public static final String f826k = ("事件标签为null或者为空字符串|onEvent接口传入的事件标签不能为null，也不能为空字符串。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: l */
    public static final String f827l = ("事件ID为null或者长度超过限制，或事件标签长度超过限制|事件ID不能为null、空串，且长度不能超过128个字符。事件标签长度不能超过256个字符。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: m */
    public static final String f828m = ("事件ID和保留字冲突|onEvent接口传入的事件ID不能和保留字冲突。详见问题链接 " + UMLogUtils.makeUrl("66946"));

    /* renamed from: n */
    public static final String f829n = ("MobclickAgent.onResume接口参数不能为null|MobclickAgent.onResume接口参数应该传入当前Activity的上下文。详见问题链接 " + UMLogUtils.makeUrl("66948"));

    /* renamed from: o */
    public static final String f830o = ("MobclickAgent.onResume接口参数不是Activity的上下文|MobclickAgent.onResume接口参数应该传入当前Activity的上下文。详见问题链接 " + UMLogUtils.makeUrl("66948"));

    /* renamed from: p */
    public static final String f831p = ("MobclickAgent.onPause接口参数不能为null|MobclickAgent.onPause接口参数应该传入当前Activity的上下文。详见问题链接 " + UMLogUtils.makeUrl("66948"));

    /* renamed from: q */
    public static final String f832q = ("MobclickAgent.onPause接口参数不是Activity的上下文|MobclickAgent.onPause接口参数应该传入当前Activity的上下文。详见问题链接 " + UMLogUtils.makeUrl("66948"));

    /* renamed from: r */
    public static final String f833r = ("@ 遗漏了Mobclick.onResume函数调用|每个Activity的onResume中都必须调用MobclickAgent.onResume。详见问题链接 " + UMLogUtils.makeUrl("66948"));

    /* renamed from: s */
    public static final String f834s = ("@ 遗漏了Mobclick.onPaused函数调用|每个Activity的onPaused中都必须调用MobclickAgent.onPaused。详见问题链接 " + UMLogUtils.makeUrl("66948"));

    /* renamed from: t */
    public static final String f835t = ("MobclickAgent.onProfileSignIn接口参数 账号ID 不能为null|账号ID不能为空。详见问题链接 " + UMLogUtils.makeUrl("66951"));

    /* renamed from: u */
    public static final String f836u = ("MobclickAgent.onProfileSignIn接口参数 账号ID 长度超过限制|账号ID 长度不能超过64个字符。详见问题链接 " + UMLogUtils.makeUrl("66951"));

    /* renamed from: v */
    public static final String f837v = ("MobclickAgent.onProfileSignIn接口参数 账号来源 长度超过限制|账号来源 长度不能超过32个字符。详见问题链接 " + UMLogUtils.makeUrl("66951"));

    /* renamed from: w */
    public static final String f838w = ("MobclickAgent.reportError方法参数context不能为null|参数Context需要指定ApplicationContext值。详见问题链接 " + UMLogUtils.makeUrl("66971"));

    /* renamed from: x */
    public static final String f839x = ("MobclickAgent.reportError方法参数error不能为null，也不能为空字符串。|error参数必须是非空字符串。详见问题链接 " + UMLogUtils.makeUrl("66971"));

    /* renamed from: y */
    public static final String f840y = ("MobclickAgent.reportError方法 Context和Throwable参数都不能为空。|参数Context需要指定ApplicationContext值，Throwable参数传入捕获到的异常对象。详见问题链接 " + UMLogUtils.makeUrl("66971"));

    /* renamed from: z */
    public static final String f841z = "MobclickAgent.setSecret方法参数context不能为null|参数Context需要指定ApplicationContext值。";
}
