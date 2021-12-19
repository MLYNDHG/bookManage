package com.Httb.bookManage.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.tomcat.util.buf.MessageBytes;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 总体功能说明：
 * <p>
 * 拦截器：
 * 将请求接口与所需参数（实体对象）全部封装成前端需要的样子返回，那么前端可以很方便的调用
 * 反射接口
 * 反射实体类
 * <p>
 * 按前端格式返回，缩短前端调用流程
 * <p>
 * <p>
 * 使用方法：加入这一个文件，然后WebMvcConfigurationSupport配置一个拦截器为DemoInterceptor即可
 * 开发时打开
 * 生产环境关闭（可在配置拦截器处读取当前环境选择是否配置拦截）
 */
@Component
public class DemoInterceptor implements HandlerInterceptor {

    /**
     * 控制器方法被调用之前
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @return 返回false就不会调用后面的方法了，
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            //判断出错进入拦截判断当前是请求Controller的js  还是model的js
            if (((HandlerMethod) handler).getBean().getClass().getName() == "org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController") {
                httpServletResponse.setContentType("text/html; charset=UTF-8");
                httpServletResponse.setCharacterEncoding("UTF-8");
                PrintWriter pw = httpServletResponse.getWriter();

                String ControllerName = getRequestControlloerName(httpServletRequest);
                StringBuffer javaScript = new StringBuffer();
                if (ControllerName.contains("model") || ControllerName.contains("Model")) {
                    javaScript = resetModel(ControllerName);
                } else if (ControllerName.contains(".") && ControllerName.contains("Controller")) {
                    ControllerMap controllerMap = getControllerMap(ControllerName);
                    javaScript = resetController(controllerMap, ControllerName);
                }
                pw.println(javaScript);
                return false;
            } else {
                return true;
            }
        } else if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        return true;
    }

    //根据Field获得对应的Class
    private Class getClassByName(Class classObject, String name) {
        Map<Class, List<Field>> fieldMap = new HashMap<>();
        Class returnClass = null;
        Class tempClass = classObject;
        while (tempClass != null) {
            fieldMap.put(tempClass, Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }

        for (Map.Entry<Class, List<Field>> entry : fieldMap.entrySet()) {
            for (Field f : entry.getValue()) {
                if (f.getName().equals(name)) {
                    returnClass = entry.getKey();
                    break;
                }
            }
        }
        return returnClass;
    }

    //递归遍历父类寻找coyoteRequest Field
    private Object findCoyoteRequest(Object request) throws Exception {
        Class a = getClassByName(request.getClass(), "request");
        Field request1 = a.getDeclaredField("request");
        request1.setAccessible(true);
        Object b = request1.get(request);
        if (getClassByName(b.getClass(), "coyoteRequest") == null) {
            return findCoyoteRequest(b);
        } else {
            return b;
        }
    }

    /**
     * 解析controllerList
     *
     * @param controllerMap  运行时得到的controllerList
     * @param controllerName 前端需要的controller的name
     * @return 返回该controllerName的所有方法拼接成的前端需要的js   请求api  字符串
     * @author 黄杰
     */
    public StringBuffer resetController(ControllerMap controllerMap, String controllerName) {

        /**
         * import request from '@/utils/request'    前端需要的模板
         * */
        /**
         *get的请求模板
         * *@param1:  id：int
         *  export function getHero(id) {
         *      return request({
         *          url:baseUrl + `/spring / get`,
         *          method:'GET',
         *          params:{
         *              id:id
         *          }
         *      })
         *}
         * */
        /**
         *POST请求模板
         *
         *@data: com.example.spring.util.QueryRequest<com.example.spring.mbg.model.Realhero>
         *export function vueTest(data) {
         *  return request({
         *      url:baseUrl + `/spring / vueTest`,
         *        method:'POST',
         *      data:data
         *  })
         *}
         * */
        //准备post请求模板
        String sentenceFormatPost = "" +
                "<pre>/*<br>" +
                "*%s<br>" +
                "*/<br>" +
                "export function %s(%s) {<br>" +
                "    return request({<br>" +
                "        url: baseUrl + `%s`,<br>" +
                "        method: 'POST',<br>" +
                "        data:%s<br>" +
                "    })<br>" +
                "}<br></pre>";
        //准备get请求模板
        String sentenceFormatGet = "" +
                "<pre>/*<br>" +
                "*%s<br>" +
                "*/<br>" +
                "export function %s(%s) {<br>" +
                "    return request({<br>" +
                "        url: baseUrl + `%s`,<br>" +
                "        method: 'GET',<br>" +
                "        params:{<br>" +
                "            %s<br>" +
                "        }<br>" +
                "    })<br>" +
                "}<br></pre>";
        StringBuffer sb = new StringBuffer("<pre>import {request,baseUrl} from '@/utils/request' <br><br></pre>");
//        for (ControllerMap controllerMapItem : listControllers) {
        String MethodString = "";
//            if (controllerMapItem.getControlleName().equals(controllerName)) {

        String MethodName;
        String Url;
        String MethodType;
        List<ControllerMap.MethodMap> methodList = new ArrayList<>();
        methodList = controllerMap.getMethodList();
        for (ControllerMap.MethodMap methodItem : methodList) {
            MethodName = methodItem.getMethodName().substring(1);
            ControllerMap.MethodAttributeMap attribute = methodItem.getAttributeList();
//                    attribute.getName();
            MethodType = attribute.getMethodType();
            Url = attribute.getUrl();
            HashMap<String, String> param = attribute.getParam();
            StringBuffer buildNotes = new StringBuffer();//*@param1: id：id\n*@param2: name:name
            StringBuffer buildArgument = new StringBuffer();//id,sex,age,name
            StringBuffer buildPass = new StringBuffer();//id:id,\n sex:sex,\n age:age, \n name:name

            Iterator<Map.Entry<String, String>> iteratorName = param.entrySet().iterator();
            while (iteratorName.hasNext()) {
                Map.Entry<String, String> entity = iteratorName.next();
                String key = entity.getKey();
                Object objValue = (Object) entity.getValue();
                String value = objValue.toString();
                buildNotes.append("@param: " + key + ":" + value);
                buildArgument.append(key + ",");
                if (MethodType.equals("GET")) {
                    buildNotes.append("<br>");
//                  buildPass.append(key + ":" + value + ",");
                    buildPass.append(key + ":" + key + ",");
                    buildPass.append("<br>");
                } else if (MethodType.equals("POST")) {
                    //此处很关键  传到浏览器尖括号会导致无法识别那么泛型类型就无法显示
                    String newbuildNotes = buildNotes.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
                    buildNotes = new StringBuffer(newbuildNotes);
                    buildPass.append(key);
                }
            }
            if (buildArgument.length() > 0) {
                buildArgument.deleteCharAt(buildArgument.length() - 1);
            }
            if (MethodType.equals("GET")) {
                MethodString = String.format(sentenceFormatGet, buildNotes, MethodName, buildArgument, Url, buildPass);
            } else if (MethodType.equals("POST")) {
                MethodString = String.format(sentenceFormatPost, buildNotes, MethodName, buildArgument, Url, buildPass);
            }
            sb.append(MethodString);
        }
//            }
//        }
        return sb;
    }

    /**
     * 获取传入的Controller中所有对象
     *
     * @param controllerName
     * @return ControllerMap
     */
    public ControllerMap getControllerMap(String controllerName) {
        ControllerMap controllerMap = new ControllerMap();
        Class controllerClass = null;
        try {
            controllerClass = Class.forName(controllerName);
            //构造器
            Constructor constructor = controllerClass.getConstructor();
            //通过构造器实例化
            Object controller = (Object) controllerClass.newInstance();
            Class<?> controllerClazz = controller.getClass();
            List<Method> declaredMethods = Arrays.asList(controllerClazz.getDeclaredMethods());

            List<ControllerMap.MethodMap> methodMapList = new ArrayList<ControllerMap.MethodMap>();

            for (int i = 0; i < declaredMethods.size(); i++) {
                ControllerMap.MethodMap methodMap = controllerMap.new MethodMap();

                ControllerMap.MethodAttributeMap methodAttributeMap = controllerMap.new MethodAttributeMap();
                GetMapping getMapping = declaredMethods.get(i).getAnnotation(GetMapping.class);
                PostMapping postMapping = declaredMethods.get(i).getDeclaredAnnotation(PostMapping.class);
                PutMapping putMapping = declaredMethods.get(i).getAnnotation(PutMapping.class);
                DeleteMapping deleteMapping = declaredMethods.get(i).getDeclaredAnnotation(DeleteMapping.class);
                HashMap<String, String> param = new HashMap<>();
                if (getMapping != null) {
                    // json串
                    String str = JSON.toJSONString(getMapping);
                    JSONObject jsonObject = JSONObject.parseObject(str);
                    String methodName = jsonObject.getString("value").substring(jsonObject.getString("value").indexOf("\"") + 1, jsonObject.getString("value").lastIndexOf("\""));
//                    methodAttributeMap.setUrl( path+methodName);
                    methodAttributeMap.setUrl(methodName);
                    methodAttributeMap.setName(methodName);
                    methodAttributeMap.setMethodType("GET");
//                    System.out.println("methodAttributeMap:"+methodAttributeMap.toString());
                }

                if (postMapping != null) {
                    // json串
                    String str = JSON.toJSONString(postMapping);
                    JSONObject jsonObject = JSONObject.parseObject(str);
                    String methodName = jsonObject.getString("value").substring(jsonObject.getString("value").indexOf("\"") + 1, jsonObject.getString("value").lastIndexOf("\""));
//                    methodAttributeMap.setUrl( path+methodName);
                    methodAttributeMap.setUrl(methodName);
                    methodAttributeMap.setName(methodName);
                    methodAttributeMap.setMethodType("POST");
//                    System.out.println("methodAttributeMap:"+methodAttributeMap.toString());
                }

                if (putMapping != null) {
//                    System.out.println("put相关的：" + JSON.toJSONString(putMapping));
                }

                if (deleteMapping != null) {
//                    System.out.println("delete相关的：" + JSON.toJSONString(deleteMapping));
                }

                //--------------获取参数名
                LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
                String[] parameterNames = discoverer.getParameterNames(declaredMethods.get(i));
                //设置private类型方法可访问
                declaredMethods.get(i).setAccessible(true);
                //获取类型
                Type[] parameterTypes = declaredMethods.get(i).getGenericParameterTypes();
                if (parameterNames != null) {
                    List listName = Arrays.asList(parameterNames);
                    if (parameterTypes != null) {
                        List listType = Arrays.asList(parameterTypes);
                        Iterator<String> iteratorName = listName.iterator();
                        Iterator<String> iteratorType = listType.iterator();
                        while (iteratorName.hasNext()) {
                            param.put(iteratorName.next(), iteratorType.next());
                        }
                        methodAttributeMap.setParam(param);
                    }
                }

                methodMap.setAttributeList(methodAttributeMap);
                methodMap.setMethodName(methodAttributeMap.getName());
                methodMapList.add(methodMap);
            }
            controllerMap.setControlleName(controllerName);
            controllerMap.setMethodList(methodMapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return controllerMap;
    }


    /**
     * 反射获取  实体类
     *
     * @param modelName
     * @return 返回前端需要的实体对象    那么进行post请求时可直接使用
     * @author 黄杰
     */
    public StringBuffer resetModel(String modelName) {
        StringBuffer restModel = new StringBuffer();
        Map<String, Object> map = new HashMap<String, Object>();
        //model实体模板
        /**
         *
         *@param damage:class java.lang.Integer
         *@param hp:class java.lang.Integer
         *@param id:class java.lang.Integer
         *@param name:class java.lang.Integer
         *export default class Point {
         *     constructor() {
         *     this.damage=0,
         *     this.hp= 0,
         *     this.id= 0,
         *     this.name= "Hero1"
         *     }
         * }
         * */
        //准备Model请求模板
        String sentenceFormatModel = "" +
                "<pre>/*" +
                "*%s" +
                "*/<br>" +
                "export default class %s {\n" +
                "    constructor() {\n" +
                "    %s" +
                "    }\n" +
                "}</pre>";

        //准备参数模板
        StringBuffer modelBufferParam = new StringBuffer();
        String sentenceFormatParam = "@param %s:%s";
        //准备备注模板
        StringBuffer modelBufferParamRemark = new StringBuffer();
        String sentenceFormatParamRemark = "this.%s=%s";
        try {
            Class modelClass1 = Class.forName(modelName);
            //构造器
            Constructor constructor = modelClass1.getConstructor();
            //通过构造器实例化
            Object model = (Object) modelClass1.newInstance();
            Class<?> clazz = model.getClass();
            List<Field> allField = new ArrayList<>();
            // 遍历往上获取父类，直至最后一个父类getSuperclass()获取父类  进行迭代
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                // 获取当前类所有的字段
                Field[] field = clazz.getDeclaredFields();
                allField.addAll(new ArrayList<>(Arrays.asList(field)));
                for (Field f : field) {
                    f.setAccessible(true);
                    try {
                        if (f.getName().equals("serialVersionUID")) {
                            continue;
                        }
//                        System.out.println("属性名：" + f.getName());
//                        System.out.println("属性类型：" + f.getGenericType());
                        String Type = f.getGenericType().toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
                        StringBuffer GenericType = new StringBuffer(Type);
                        String Param = String.format(sentenceFormatParam, f.getName(), GenericType);
                        modelBufferParam.append(Param + "<br>");

                        String ParamRemark = String.format(sentenceFormatParamRemark, f.getName(), "''");
                        modelBufferParamRemark.append(ParamRemark + "<br>");
//                        System.out.println("属性值：" + f.get(model).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            String modelNameFormat = modelName.split("\\.")[modelName.split("\\.").length - 1];
            String modelStringParam = String.format(sentenceFormatModel, modelBufferParam, modelNameFormat, modelBufferParamRemark);
            restModel = new StringBuffer(modelStringParam);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return restModel;
    }

    /**
     * 框架会把错误 请求的url  藏到uriMB中  因此需要特意寻找
     * 从httpServletRequest中获取ControllerName
     *
     * @return
     */
    public String getRequestControlloerName(HttpServletRequest httpServletRequest) throws Exception {

        Object a = findCoyoteRequest(httpServletRequest);
        Field coyoteRequest = a.getClass().getDeclaredField("coyoteRequest");
        coyoteRequest.setAccessible(true);
        Object b = coyoteRequest.get(a);
        Field uriMB = b.getClass().getDeclaredField("uriMB");
        uriMB.setAccessible(true);
        MessageBytes c = (MessageBytes) uriMB.get(b);
        String ControllerName = c.getString().split("/")[c.getString().split("/").length - 1];

        return ControllerName;
    }


}

//保存controller对象
@Data
class ControllerMap {
    private String controlleName;
    private List<MethodMap> methodList;

    @Data
    public class MethodMap {
        private String methodName;
        private MethodAttributeMap attributeList;
    }

    @Data
    public class MethodAttributeMap {
        private String url;
        private String name;
        private String methodType;
        private HashMap<String, String> param = new HashMap<>();
    }
}