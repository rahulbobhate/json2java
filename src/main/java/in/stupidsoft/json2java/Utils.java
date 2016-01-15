package in.stupidsoft.json2java;

public class Utils 
{
	public static String getSimpleClassName(final String className)
	{
		return className.substring(className.lastIndexOf('.')+1);
	}	
	
	public static String getPathName(final Preference preference)
	{
		return preference.getSourcePath() + preference.getPackageName().replace('.', '/') + "/" +preference.getClassName() + ".java";
	}
}