package in.stupidsoft.json2java;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Json2JavaCovereter 
{
	private Preference preference;
	
	/**
	 * 
	 * @param preference
	 */
	public Json2JavaCovereter(final Preference preference) 
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

	@SuppressWarnings("unchecked")
	public Class<?> convertToClass(final String json) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException
	{
		final ObjectMapper mapper = new ObjectMapper();
		final Map<String, Object> map = mapper.readValue(json, Map.class);
		
		final File file = createJavaFile(map);
		final Class<?> c = StupidCompiler.compileJavaFileAndLoadClass(file, this.preference);
		
		return c;
	}
	
	public Object convertToObject(final String json) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException
	{
		final ObjectMapper mapper = new ObjectMapper();
		final Class<?> clazz = convertToClass(json);
		return mapper.readValue(json, clazz);
	}
	
	private File createJavaFile(final Map<String, Object> map) throws IOException, ClassNotFoundException
	{
		final File file = new File(Utils.getPathName(this.preference));
		final String content = new ContentBuilder(this.preference).getContent(map);
		FileUtils.writeStringToFile(file, content);		
		
		return file;
	}	
}