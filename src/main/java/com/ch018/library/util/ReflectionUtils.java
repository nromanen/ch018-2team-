package com.ch018.library.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.ch018.library.util.annotation.Between;

public class ReflectionUtils {

		
		public List<Field> getOrdinaryFields(Class<?> clazz) {
			Field[] allFields = clazz.getDeclaredFields();
			List<Field> filteredFields = new ArrayList<>();
			for(Field field : allFields) {
				
				if(Modifier.isFinal(field.getModifiers()) 
						|| Modifier.isStatic(field.getModifiers())
						|| field.getDeclaredAnnotations().length > 0)
					continue;
				else {
					filteredFields.add(field);	
				}
			}
			
			
			return filteredFields;
		}
		
		
		public List<Field[]> getBetweenFields(Class<?> clazz) {
			Field[] allFields = clazz.getDeclaredFields();
			List<Field[]> filteredFields = new ArrayList<>();
			
			for(Field field : allFields) {
				if(field.isAnnotationPresent(Between.class)) {
	
					if(field.getAnnotation(Between.class).value().equals("start")) {
						Field[] returnFields = new Field[2];
						field.setAccessible(true);
						returnFields[0] = field;
						String FieldName = field.getAnnotation(Between.class).name();
						for(Field fieldEnd : allFields) {
							if(fieldEnd.isAnnotationPresent(Between.class) 
									&& fieldEnd.getAnnotation(Between.class).name().equals(FieldName) 
									&& fieldEnd.getAnnotation(Between.class).value().equals("end")) {
								returnFields[1] = fieldEnd;
								fieldEnd.setAccessible(true);
								filteredFields.add(returnFields);
								break;
							}
						}
					}
				}
					
				}
			
			return filteredFields;
		}
		
		public List<Field> getFieldsByAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
			Field[] allFields = clazz.getDeclaredFields();
			List<Field> filteredFields = new ArrayList<>();
			
			for(Field field : allFields) {
				if(field.isAnnotationPresent(annotation))
					filteredFields.add(field);
			}
			
			return filteredFields;
		}
}
