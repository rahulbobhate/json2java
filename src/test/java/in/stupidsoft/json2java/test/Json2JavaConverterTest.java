package in.stupidsoft.json2java.test;

import static org.junit.Assert.*;
import in.stupidsoft.json2java.Json2JavaCovereter;
import in.stupidsoft.json2java.Preference;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Json2JavaConverterTest 
{
	private Json2JavaCovereter json2JavaCovereter;
	
	@Before
	public void setup()
	{
		assertNotNull(ToolProvider.getSystemJavaCompiler());	
	}
	
	@Test
	public void testConvertWithSimpleJson() throws IOException, ClassNotFoundException
	{
		
		final Preference preference = new Preference.Builder()
										.packageName("in.stupidsoft.json2java.gen")
										.className("SimpleClass")
										.sourcePath("src/main/java/")
										.classPath("target/classes/")
										.build();
		
		json2JavaCovereter = new Json2JavaCovereter(preference);
		
		final String json = FileUtils.readFileToString(new File("src/test/resources/simple_json.json"));

		final Class<?> clazz = json2JavaCovereter.convertToClass(json);
		assertNotNull(clazz);
		assertEquals(clazz.getName(), "in.stupidsoft.json2java.gen.SimpleClass");		
	}
	
	@Test
	public void testConvertWithSimpleJsonWithBlankArray() throws IOException, ClassNotFoundException, NoSuchFieldException, SecurityException
	{		
		final Preference preference = new Preference.Builder()
										.packageName("in.stupidsoft.json2java.gen")
										.className("SimpleClassWithBlankArray")
										.sourcePath("src/main/java/")
										.classPath("target/classes/")
										.build();
		
		json2JavaCovereter = new Json2JavaCovereter(preference);
		
		final String json = FileUtils.readFileToString(new File("src/test/resources/simple_json_with_blank_array.json"));

		final Class<?> clazz = json2JavaCovereter.convertToClass(json);
		assertNotNull(clazz);
		assertEquals(clazz.getName(), "in.stupidsoft.json2java.gen.SimpleClassWithBlankArray");		
		checkSimpleClassFields(clazz);
	}
	
	@Test
	public void testConvertWithSimpleJsonWithStringArray() throws IOException, ClassNotFoundException, NoSuchFieldException, SecurityException
	{		
		final Preference preference = new Preference.Builder()
										.packageName("in.stupidsoft.json2java.gen")
										.className("SimpleClassWithStringArray")
										.sourcePath("src/main/java/")
										.classPath("target/classes/")
										.build();
		
		json2JavaCovereter = new Json2JavaCovereter(preference);
		
		final String json = FileUtils.readFileToString(new File("src/test/resources/simple_json_with_string_array.json"));

		final Class<?> clazz = json2JavaCovereter.convertToClass(json);
		assertNotNull(clazz);
		assertEquals(clazz.getName(), "in.stupidsoft.json2java.gen.SimpleClassWithStringArray");
		checkSimpleClassFields(clazz);		
	}
	
	@Test
	public void testConvertWithSimpleJsonWithChildObject() throws IOException, ClassNotFoundException, NoSuchFieldException, SecurityException
	{		
		final Preference preference = new Preference.Builder()
										.packageName("in.stupidsoft.json2java.gen")
										.className("SimpleClassWithChildObject")
										.sourcePath("src/main/java/")
										.classPath("target/classes/")
										.build();
		
		json2JavaCovereter = new Json2JavaCovereter(preference);
		
		final String json = FileUtils.readFileToString(new File("src/test/resources/simple_json_with_child_object.json"));

		final Class<?> clazz = json2JavaCovereter.convertToClass(json);
		assertNotNull(clazz);
		assertEquals(clazz.getName(), "in.stupidsoft.json2java.gen.SimpleClassWithChildObject");
		checkSimpleClassFields(clazz);
	}
	
	@After
	public void tearDown() throws IOException
	{
		FileUtils.deleteDirectory(new File("src\\main\\java\\in\\stupidsoft\\json2java\\gen"));
		FileUtils.deleteDirectory(new File("target\\classes\\in\\stupidsoft\\json2java\\gen"));
	}
	
	private void checkSimpleClassFields(final Class<?> clazz) throws NoSuchFieldException, SecurityException
	{
		final Field name = clazz.getDeclaredField("name");
		assertNotNull(name);
		assertEquals(String.class, name.getType());
		
		final Field age = clazz.getDeclaredField("age");
		assertNotNull(age);
		assertEquals(Integer.class, age.getType());
		
		final Field salary = clazz.getDeclaredField("salary");
		assertNotNull(salary);
		assertEquals(Double.class, salary.getType());
		
		final Field isAlive = clazz.getDeclaredField("isAlive");
		assertNotNull(isAlive);
		assertEquals(Boolean.class, isAlive.getType());
	}
}