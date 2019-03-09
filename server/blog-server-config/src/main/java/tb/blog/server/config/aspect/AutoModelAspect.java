package tb.blog.server.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import tb.blog.server.config.util.UserContext;
import tb.blog.server.data.entity.BaseEntity;

import java.util.Collection;
import java.util.Date;

/**
 * @author TB
 */
@Aspect
@Component
public class AutoModelAspect {
    @Pointcut("@annotation(tb.blog.server.config.annotation.AutoModel)")
    public void autoModelCut() {

    }

    @Before("autoModelCut()")
    public void dataFilter(JoinPoint point) {
        Object[] params = point.getArgs();
        for (Object param : params) {
            if (param instanceof BaseEntity) {
                insertCreator(param);
                return;
            }

            if (param instanceof Collection && !((Collection) param).isEmpty()) {
                for (Object item : ((Collection) param)) {
                    if (item instanceof BaseEntity) {
                        insertCreator(item);
                    }
                }
                return;
            }
        }
    }

    private void insertCreator(Object entity) {
        if (((BaseEntity) entity).getId() == null) {
            Long userName = UserContext.get();
            ((BaseEntity) entity).setCreateBy(userName);
            ((BaseEntity) entity).setCreateTime(new Date());
        }
    }
}
