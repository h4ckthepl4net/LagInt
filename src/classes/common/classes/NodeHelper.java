package classes.common.classes;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javafx.scene.Node;

public class NodeHelper {

    private Node child;

    public NodeHelper() {
        this.child = null;
    }
    public NodeHelper(Node child) {
        this.setChild(child);
    }

    public void setChild(Node child) {
        this.child = child;
    }
    public Node getChild() {
        return this.child;
    }

    public Method getMethodAndHandleExceptions(String methodName) {
        return this.getMethodAndHandleExceptions(methodName, false);
    }
    public Method getMethodAndHandleExceptions(String methodName, boolean logging) {
        if(this.child == null)
            throw new AssertionError("Child node of class NodeHelper must not be null");
        Method methodToGet = null;
        try {
            methodToGet = this.child.getClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            if(logging) {
                System.out.println(AnsiUtils.ANSI_RED +
                        "Error in ChildAdapter@getMethod(): Reflection can not get method " + e.getMessage() +
                        AnsiUtils.ANSI_RESET);
            }
        }
        return methodToGet;
    }

    public Object invokeMethodAndHandleExceptions(Method methodToInvoke) {
        return this.invokeMethodAndHandleExceptions(methodToInvoke, false);
    }
    public Object invokeMethodAndHandleExceptions(Method methodToInvoke, boolean logging) {
        if(this.child == null)
            throw new AssertionError("Child node of class NodeHelper must not be null");
        Object result = null;
        try {
            result = methodToInvoke.invoke(this.child);
        } catch (IllegalAccessException e) {
            if(logging) {
                System.out.println(AnsiUtils.ANSI_RED +
                        "Error in ChildAdapter@invokeMethod(): Reflection can not access " +
                        child.getClass().getName() + methodToInvoke.getName() +
                        AnsiUtils.ANSI_RESET);
            }
        } catch (InvocationTargetException e) {
            if(logging) {
                System.out.println(AnsiUtils.ANSI_RED +
                        "Error in ChildAdapter@invokeMethod(): Method " +
                        child.getClass().getName() + methodToInvoke.getName() + " threw an exception on invocation" +
                        AnsiUtils.ANSI_RESET);
            }
        }
        return result;
    }

    public Method getFirstMatchedMethod(List<String> methodNames) throws NoSuchMethodException {
        return this.getFirstMatchedMethod(methodNames.toArray(new String[0]));
    }
    public Method getFirstMatchedMethod(String[] methodNames) throws NoSuchMethodException {
        for(String methodName : methodNames) {
            Method method = this.getMethodAndHandleExceptions(methodName);
            if(method != null) {
                return method;
            }
        }
        throw new NoSuchMethodException("No method found from given array");
    }
}
