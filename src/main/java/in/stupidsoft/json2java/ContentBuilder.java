package in.stupidsoft.json2java;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.text.WordUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ContentBuilder 
{
	private Preference preference;

	/**
	 * @param preference
	 */
	public ContentBuilder(final Preference preference) 
	{
		super();
		this.preference = preference;
	}

	/**
	 * @return the preference
	 */
	public Preference getPreference() 
	{
		return this.preference;
	}

	/**
	 * @param preference the preference to set
	 */
	public void setPreference(final Preference preference)
	{
		this.preference = preference;
	}
	
	public String getContent(final Map<String, Object> map) throws JsonParseException, JsonMappingException, JsonGenerationException, ClassNotFoundException, IOException 
	{
		final StringBuilder builder = new StringBuilder();
		
		// Write the package name in file
		writePackage(builder);
		
		// Write imports in file
		writeImports(builder, map);
		
		// Write the class name
		writeClassName(builder);
		
		// Start of class body
		builder.append("{");
		builder.append("\n");
		
		writeMembers(builder, map);
		
		// End of the class body
		builder.append("}");
		
		return builder.toString();
	}

	private void writePackage(final StringBuilder builder)
	{
		// Append package name in builder
		builder.append("package " + this.preference.getPackageName() + ";");
		builder.append("\n");
		builder.append("\n");
	}
	
	private void writeImports(final StringBuilder builder, final Map<String, Object> map) throws JsonParseException, JsonMappingException, JsonGenerationException, ClassNotFoundException, IOException
	{
		for (Entry<String, Object> entry : map.entrySet()) 
		{
			final Object value = entry.getValue();
			
			if(value != null)
			{
				final String className;
				
				if(value instanceof Map)
				{
					final Preference newPreference = new Preference(this.preference);
					newPreference.setClassName(WordUtils.capitalize(entry.getKey()));
					
					//TODO: Find a way to do this more efficiently.
					// Creating an object of the child class to get the class name and to create and load the class.
					final Object object = new Json2JavaCovereter(newPreference).convertToObject(new ObjectMapper().writeValueAsString(value));
					className = object.getClass().getName();
					entry.setValue(object);
				}
				else
				{
					className = value.getClass().getName();
				}
				
				builder.append("import " + className + ";");
				builder.append("\n");
			}
		}
		
		builder.append("\n");
	}
	
	private void writeClassName(final StringBuilder builder)
	{
		builder.append("public class " + this.preference.getClassName());
		builder.append("\n");
	}
		
	private void writeMembers(final StringBuilder builder, final Map<String, Object> map) 
	{		
		final Set<Entry<String, Object>> entrySet = map.entrySet();
		
		for (Entry<String, Object> entry : entrySet) 
		{
			final String key = entry.getKey();
			final Object value = entry.getValue()!=null?entry.getValue():new Object();
			
			final String type = value.getClass().getSimpleName();
			
			writeVariable(builder, type, key);
			writeGetter(builder, type, key);
			writeSetter(builder, type, key);
			
			builder.append("\n");
		}		
	}
	
	private void writeVariable(final StringBuilder builder, final String type, final String name)
	{
		builder.append("private " + type + " " + name + ";");
		builder.append("\n");
	}
	
	private void writeGetter(final StringBuilder builder, final String type, final String name)
	{
		builder.append("public " + type + " get" + WordUtils.capitalize(name) + "()");
		builder.append("{");
		builder.append("return this." + name + ";");
		builder.append("}");
		builder.append("\n");				
	}
	
	private void writeSetter(final StringBuilder builder, final String type, final String name)
	{
		builder.append("public void set" + WordUtils.capitalize(name) + "(" + type + " " + name + ")");
		builder.append("{");
		builder.append("this." + name + " = " + name + ";");
		builder.append("}");
		builder.append("\n");
	}
}