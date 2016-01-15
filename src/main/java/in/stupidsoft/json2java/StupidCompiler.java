package in.stupidsoft.json2java;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class StupidCompiler 
{
	public static Class<?> compileJavaFileAndLoadClass(final File source, final Preference preference) throws MalformedURLException, ClassNotFoundException 
	{
		final File classPath = new File(preference.getClassPath());
		classPath.mkdirs();
		
		final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		compiler.run(null, null, null, "-d", classPath.getAbsolutePath(), source.getAbsolutePath());
				
		final URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { classPath.toURI().toURL()});
		final Class<?> cls = Class.forName(preference.getPackageName() +"."+ preference.getClassName(), true, classLoader);
		
		return cls;
	}	
}