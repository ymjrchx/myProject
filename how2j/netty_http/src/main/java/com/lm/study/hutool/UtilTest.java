package com.lm.study.hutool;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.util.*;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathConstants;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.lm.study.hutool.PrintUtil.*;
/**
 * @author chenxin
 * @date 2019/7/15 13:49
 */
public class UtilTest {
    @Test
    public void StrUtil(){
        String fileName = StrUtil.removeSuffix("pretty_girl.jpg", ".jpg") ; //fileName -> pretty_girl
        String str = "abcdefgh";
        String strSub1 = StrUtil.sub(str, 2, 3); //strSub1 -> c
        String strSub2 = StrUtil.sub(str, 2, -3); //strSub2 -> cde
        String strSub3 = StrUtil.sub(str, 3, 2); //strSub2 -> c
        String template = "{}爱{}，就像老鼠爱大米";
        String str1 = StrUtil.format(template, "我", "你"); //str -> 我爱你，就像老鼠爱大米
        pl(str1);

    }
    @Test
    public void HexUtil(){
        String str = "我是一个字符串";

        String hex = HexUtil.encodeHexStr(str, CharsetUtil.CHARSET_UTF_8);
        pl(hex);

//hex是：
//e68891e698afe4b880e4b8aae5ad97e7aca6e4b8b2

        String decodedStr = HexUtil.decodeHexStr(hex);
        pl(decodedStr);

//解码后与str相同

    }

    @Test
    public void EscapeUtil() throws UnsupportedEncodingException {
        String str = EscapeUtil.escape("我爱中华");
        pl(str);
        pl(EscapeUtil.safeUnescape(str));
        pl(HexUtil.encodeHexStr("我",CharsetUtil.CHARSET_UTF_8));
        pl( Integer.toHexString((int)'我'));

    }

    /**
     * 这些算法包括：
     *
     * additiveHash 加法hash
     * rotatingHash 旋转hash
     * oneByOneHash 一次一个hash
     * bernstein Bernstein's hash
     * universal Universal Hashing
     * zobrist Zobrist Hashing
     * fnvHash 改进的32位FNV算法1
     * intHash Thomas Wang的算法，整数hash
     * rsHash RS算法hash
     * jsHash JS算法
     * pjwHash PJW算法
     * elfHash ELF算法
     * bkdrHash BKDR算法
     * sdbmHash SDBM算法
     * djbHash DJB算法
     * dekHash DEK算法
     * apHash AP算法
     * tianlHash TianL Hash算法
     * javaDefaultHash JAVA自己带的算法
     * mixHash 混合hash算法，输出64位的值
     */
    @Test
    public void HashUtil(){
        pl(HashUtil.elfHash("JAVA自己带的算法"));
        pl(HashUtil.apHash("JAVA自己带的算法"));

    }

    @Test
    public void URLUtil(){
        URLUtil.url("dddd");

    }
    @Test
    public void XmlUtil(){
        Document document = XmlUtil.readXML("file.xml");
        pl(document);
        FileReader fileReader = new  FileReader("D:\\dgg项目资料\\pom.xml");
        String str = fileReader.readString();
        pl(str);
        Document document1 = XmlUtil.parseXml(str);
        pl(document1);
        pl(document1.getTextContent());
        Document docResult=XmlUtil.readXML("file.xml");
//结果为“ok”
        Object value = XmlUtil.getByXPath("//returnsms/message", docResult, XPathConstants.STRING);
        pl(value);

    }

    @Test
    public void ObjectUtil(){
        ObjectUtil.equal("dd","dd");
        ObjectUtil.length("dddge");
        ObjectUtil.contains("dddd","dddd");
        pl(ObjectUtil.isBasicType('c'));


    }

    @Test
    public void ReflectUtil(){
        Method[] methods = ReflectUtil.getMethods(UtilTest.class);
        for (Method method : methods){
            System.out.println(method.getName());
            System.out.println(method.getDeclaringClass());
            System.out.println(method.getGenericParameterTypes());
        }
        Method method = ReflectUtil.getMethod(UtilTest.class, "ReflectUtil");
        UtilTest.class.getMethods();
        UtilTest utilTest = ReflectUtil.newInstance(UtilTest.class);
        System.out.println(utilTest);

        TestClass testClass = new TestClass();
        ReflectUtil.invoke(testClass, "setA", 10);
        System.out.println(testClass.getA());

    }
    class TestClass {
        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }

    @Test
    public void TypeUtil(){
        Method method = ReflectUtil.getMethod(TestClass1.class,"intTest",Integer.class);
        Type type = TypeUtil.getParamType(method,0);
        System.out.println(type.getClass());
        System.out.println(type.getTypeName());

        Method method1 = ReflectUtil.getMethod(TestClass1.class,"getList");
        Type type1 = TypeUtil.getReturnType(method);
        System.out.println(type1.getTypeName());
        System.out.println(type1.getClass());
        Method method2 = ReflectUtil.getMethod(TestClass1.class,"getList");
        Type type2 = TypeUtil.getReturnType(method);
        Type type3 = TypeUtil.getTypeArgument(type);


    }
    public static class TestClass1 {
        public List<String> getList(){
            return new ArrayList<String>();
        }

        public Integer intTest(Integer integer) {
            return 1;
        }
    }

    @Test
    public void PageUtil(){
        int[] startEnd1 = PageUtil.transToStartEnd(1,10);
        System.out.println(Arrays.toString(startEnd1));
        int [] startEnd2 = PageUtil.transToStartEnd(2,10);
        System.out.println(Arrays.toString(startEnd2));
        int totalPage = PageUtil.totalPage(20,3);
        System.out.println(totalPage);
        int[] rainbow = PageUtil.rainbow(6, 20, 6);
        System.out.println(Arrays.toString(rainbow));
    }

    @Test
    public void ClipboardUtil(){
        ClipboardUtil.setStr("dddddgqwgqg");
        System.out.println(ClipboardUtil.getStr());


    }

    @Test
    public void ClassUtil() throws IllegalAccessException, InstantiationException {
        Class<Class<UtilTest>> aClass = ClassUtil.getClass(UtilTest.class);
        System.out.println(aClass.getName());
        System.out.println(aClass.getCanonicalName());
        Set<Class<?>> classes = ClassUtil.scanPackage();
      /*  for (Class x : classes){
            System.out.println(x.getName());
        }*/

        System.out.println(ClassUtil.getClassPath());
        System.out.println(ClassUtil.getEnclosingClass(TestClass1.class));
        System.out.println(TestClass1.class.getSimpleName());
        System.out.println(TestClass1.class.getCanonicalName());
        System.out.println(TestClass1.class.getName());
        System.out.println(new int[2].getClass().getComponentType());
        int [] nums = new int[]{1,3};
        Class clazz = nums.getClass().getComponentType();
        System.out.println(clazz.getName());
        System.out.println(clazz.getCanonicalName());

    }
    @Test
    public void ClassLoaderUtil(){

    }

    @Test
    public void EnumUtil(){

    }

    @Test
    public void RuntimeUtil(){

    }

    @Test
    public void NumberUtil(){

    }

    @Test
    public void ArrayUtil(){

    }

    @Test
    public void RandomUtil(){

    }

    @Test
    public void NetUtil(){

    }

    @Test
    public void IdUtil(){

    }

    @Test
    public void ZipUtil(){

    }

    @Test
    public void ReferenceUtil(){

    }

    @Test
    public void ReUtil(){

    }










}
