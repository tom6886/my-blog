package tb.blog.server.config.util;

/**
 * @author tangbo
 */
public class UserContext {
    private static final ThreadLocal<Long> LOCAL = new ThreadLocal<Long>();

    public static void set(Long id) {
        LOCAL.set(id);
    }

    public static Long get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}
