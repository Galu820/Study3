package org.example;

        import java.lang.reflect.InvocationHandler;
        import java.lang.reflect.Method;
        import java.util.HashMap;

public class FractionableInvHandler implements InvocationHandler
{
    private Object obj;

  FractionableInvHandler(Object obj){
        this.obj = obj;
        this.caches = new HashMap<>();
    }
    private Object cachedValue;
    private HashMap<String, Object> caches;
    boolean hasMutatorAnn = false;
    boolean hasCacheAnn = false;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = obj.getClass().getMethod(method.getName(), method.getParameterTypes());
        if(m.isAnnotationPresent(Cache.class)){
            hasCacheAnn = true;
            System.out.println("Found @Cache annotation ");
        }

        if (m.isAnnotationPresent(Mutator.class)) {
            hasMutatorAnn = true;
            System.out.println("Found @Mutator annotation ");
        }

        if (hasMutatorAnn) {
            caches.clear();
            //cachedValue = method.invoke(obj, args);
            //caches.put(m.getName(), cachedValue);
            System.out.println("Not cashed value from mutator "+m.getName());
            return method.invoke(obj, args);
        }
        else {
            if (hasCacheAnn) {
                //System.out.println("!!! "+caches.get(m.getName()));
                if (!caches.containsKey(m.getName())) {
                    System.out.println("Not cached value from "+m.getName()+": ");
                    cachedValue = method.invoke(obj, args);
                    caches.put(m.getName(), cachedValue);
                    return cachedValue;
                } else {
                    System.out.println("Cached value from "+m.getName()+": ");
                    return caches.get(m.getName());
                }
            } else return method.invoke(obj, args);
        }
    }
}