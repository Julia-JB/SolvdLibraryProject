package extra;

import user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionPractice {
	public static void invokeMethod() throws Exception {
		// Creating an object and invoking the method using Reflection

		Class<?> mediaCenterReflection = Class.forName("mediaCenter.MediaCenter");
		Method methodReflection = mediaCenterReflection.getMethod("printUsageCost", User.class,
				int.class, int.class);
		Object objectReflection = mediaCenterReflection.newInstance();

		Class<?> userCl = Class.forName("user.User");
		Object userObject = new User(12, "John Still", "johnstill@gmail.com", "8054701222", true);

		methodReflection.invoke(objectReflection, userObject, 2, 4);
	};


	public static void getClassDetails() throws Exception {
		Logger logger = LogManager.getLogger();

		// Creating the class
		Class<?> mediaCenterReflection = Class.forName("mediaCenter.MediaCenter");

		// Logging details about fields
		logger.info("Fields: ");
		Field[] fields = mediaCenterReflection.getDeclaredFields();
		Arrays.stream(fields)
				.forEach(field -> {
					logger.info("Name: " + field.getName());
					logger.info("Type: " + field.getType().getSimpleName());
					logger.info("Access modifier: " + Modifier.toString(field.getModifiers()));
				});

		// Logging details about the constructor
		logger.info("Constructor: ");
		Constructor<?> constructor = mediaCenterReflection.getDeclaredConstructor();
		logger.info("Name: " + constructor.getName());
		logger.info("Access modifier: " + Modifier.toString(constructor.getModifiers()));

		// Logging details about the methods
		logger.info("Methods: ");
		Method[] methods = mediaCenterReflection.getMethods();
		Arrays.stream(methods)
				.forEach(method -> {
					logger.info("Method name: " + method.getName());
					logger.info("Return type: " + method.getReturnType().getSimpleName());
					logger.info("Access modifier: " + Modifier.toString(method.getModifiers()));
					Parameter[] parameters = method.getParameters();
					Arrays.stream(parameters)
							.forEach(parameter -> {
								logger.info("Name: " + parameter.getName());
								logger.info("Parameter type: " + parameter.getType());
							});
				});
	}

}

