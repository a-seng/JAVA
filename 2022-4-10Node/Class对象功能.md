# *Class对象功能

​	*获取功能：

​		1.获取成员变量们

​			*Field[] getFields()*  获取所有public修饰的成员变量

​			*Field getField(String name)*

​			*Field[] getDeclareFields()*	private 修饰的也可以获取
​			*Field getDeclaredField(String name)*
​		2.获取构造方法们

​			*Constructor<?>[] getConstructors()*
​			*Constructor<T> getConstructor(类<?>... parameterTypes)*
​			*Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)*
​			Constructor<T>[] getDeclaredConstructors()
​		3.获取成员方法们

​			*Method[] getMethods()*
​			*Method getMethod(String name,类<?>... parameterTypes)*
​			Methos[] getDeclaredMethods()
​			Method getDeclaredMethod(String name,类<?>... parameterTypes)

​		4.获取类名

​			*String getName()*

# *Fiels:成员变量*

​	*操作:* 

​		1.设置值

​			*void set(Object obj , Object value)*

​		2.获取值

​			*get(Object obj)*

​		3.忽略访问权限修饰符的安全检查

​			*setAccessible（true) ：暴力反射

newInsrance（）构造方法



JDK预定义注解

​	*@Override :检测被该注解标注的方法是否是继承自父类的

​	*@Deprecated: 该注解标注的内容，表示已过时

​	*@SuppressWarnings: 压制警告*

@SupperssWarnings("all")



抽象的放发为属性

必须给他赋值

不赋值就报错

如果你只有一个属性需要赋值 ，并且这个属性名称是value 这个value可以不用写，直接定义值  

可以default 就不用赋值 默认为多少

public @interface MyAnno{

int age();

String name()default "asneg";

}

@MyAnno(age=1)

public class Worker{

}

枚举类型赋值

@MyAnno(value=12,per=Person.P1,anno2=@MyAnno2,strs={"aaa","bbb"})

//一般      赋值      枚举赋值  注解赋值   数组赋值，值使用{}包括 如果只有一个 {}可以不写

元注解:用于描述注解的注解

​	@Target： 描述注解能够作用的位置

​		ElementType.Type可以作用于类上

​		ElementType.METHOD方法上

​		ElementType.FIELD成员变量上

# 	@Retention: 描述注解被保留的阶段

​	@Retent(RetentionPolicy.RUNTIME)当前被描述的注解，会被保留到class字节码文件中，并被JVM读取到

​	@Documented: 描述注解是否被抽取到api文档中

​	@Inherited: 描述注解是否被子类继承



public class ProImpl implements Pro{
	public String className(){
		return "cn.itcast.annotation.Demo1";	
	}
		public String methodName(){
			return "show";		
}

}

