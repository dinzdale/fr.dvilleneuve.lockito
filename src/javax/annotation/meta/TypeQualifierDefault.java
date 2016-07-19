package javax.annotation.meta;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface TypeQualifierDefault
{
  ElementType[] value() default {};
}

/* Location:
 * Qualified Name:     javax.annotation.meta.TypeQualifierDefault
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */