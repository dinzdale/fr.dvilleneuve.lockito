package fr.dvilleneuve.lockito.core.manager;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface BusMethod {}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.BusMethod
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */