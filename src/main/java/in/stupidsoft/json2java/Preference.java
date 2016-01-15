package in.stupidsoft.json2java;

/**
 * 
 * @author Rahul Bobhate
 *
 */
public class Preference 
{
	private String packageName;
	private String className;
	private String classPath;
	private String sourcePath;
	private boolean useStaticNestedClasses;
	
	private Preference(final Builder builder)
	{
		this.packageName = builder.packageName;
		this.className = builder.className;
		this.classPath = builder.classPath;
		this.sourcePath = builder.sourcePath;
		this.useStaticNestedClasses = builder.useStaticNestedClasses;
	}
	
	public Preference(final Preference preference)
	{
		this.packageName = preference.packageName;
		this.className = preference.className;
		this.classPath = preference.classPath;
		this.sourcePath = preference.sourcePath;
		this.useStaticNestedClasses = preference.useStaticNestedClasses;		
	}
	
	/**
	 * @return the packageName
	 */
	public String getPackageName() 
	{
		return this.packageName;
	}

	/**
	 * @return the className
	 */
	public String getClassName() 
	{
		return this.className;
	}
	
	/**
	 * @return the classPath
	 */
	public String getClassPath() 
	{
		return this.classPath;
	}
	
	/**
	 * @return the classPath
	 */
	public String getSourcePath() 
	{
		return this.sourcePath;
	}
	
	
	/**
	 * @return the useStaticNestedClasses
	 */
	public boolean isUseStaticNestedClasses() 
	{
		return this.useStaticNestedClasses;
	}

	
	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(final String packageName) 
	{
		this.packageName = packageName;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(final String className) 
	{
		this.className = className;
	}

	/**
	 * @param classPath the classPath to set
	 */
	public void setClassPath(final String classPath) 
	{
		this.classPath = classPath;
	}

	/**
	 * @param sourcePath the sourcePath to set
	 */
	public void setSourcePath(final String sourcePath) 
	{
		this.sourcePath = sourcePath;
	}

	/**
	 * @param useStaticNestedClasses the useStaticNestedClasses to set
	 */
	public void setUseStaticNestedClasses(final boolean useStaticNestedClasses) 
	{
		this.useStaticNestedClasses = useStaticNestedClasses;
	}

	/**
	 * 
	 * @author Rahul Bobhate
	 *
	 */
	public static class Builder
	{
		private String packageName;
		private String className;
		private String classPath;
		private String sourcePath;
		private boolean useStaticNestedClasses;
		
		public Builder packageName(final String packageName)
		{
			this.packageName = packageName;
			return this;
		}
		
		public Builder className(final String className)
		{
			this.className = className;
			return this;
		}
		
		public Builder classPath(final String classPath)
		{
			this.classPath = classPath;
			return this;
		}	
		
		public Builder sourcePath(final String sourcePath)
		{
			this.sourcePath = sourcePath;
			return this;
		}
		
		public Builder useStaticNestedClasses(final boolean useStaticNestedClasses)
		{
			this.useStaticNestedClasses = useStaticNestedClasses;
			return this;
		}
		
		public Preference build()
		{
			return new Preference(this);
		}
	}
}
