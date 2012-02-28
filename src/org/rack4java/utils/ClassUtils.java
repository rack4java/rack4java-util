package org.rack4java.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

public class ClassUtils {
    
	@SuppressWarnings("rawtypes")
	protected static Class loadClass(String className, ClassLoader loader)
            throws ClassNotFoundException {
        return Class.forName(className, true, loader);
    }

	@SuppressWarnings("rawtypes")
	public static Object rawCreate(Class cls)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return cls.newInstance();
    }

    public static Object rawCreate(String className, ClassLoader loader)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return loadClass(className, loader).newInstance();
    }

    protected static void logCreationMessage(String message, Exception e, Writer out) {
        if (message != null && out != null) {
            try {
				out.write(message);
	            out.write("\r\n");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            e.printStackTrace();
        }
    }

    protected static void logCreationMessage(String message, Exception e,
            PrintStream out) {
        if (message != null && out != null) {
            out.println(message);
            e.printStackTrace(out);
        }
    }

    protected static void logCreationException(String className, Exception e,
            Writer out) {
        logCreationMessage(creationMessage(className, e), e, out);
    }

    protected static void logCreationException(String className, Exception e,
            PrintStream out) {
        logCreationMessage(creationMessage(className, e), e, out);
    }

    private static String creationMessage(String className, Exception e) {
        String message;
        if (e instanceof ClassNotFoundException) {
            message = "couldn't find class '" + className + "'";
        } else if (e instanceof IllegalAccessException) {
            message = "couldn't access class '" + className + "'";
        } else if (e instanceof InstantiationException) {
            message = "couldn't instantiate class '" + className + "'";
        } else {
            message = "Unrecognized exception during object creation";
        }
        return message;
    }

    public static Object createObject(String className, ClassLoader loader) {
        Object ret = null;

        try {
            ret = rawCreate(className, loader);
        } catch (Exception e) {
            logCreationException(className, e, System.err);
        }

        return ret;
    }

    public static Object createObject(String className) {
        return createObject(className, ClassUtils.class.getClassLoader());
    }
}
