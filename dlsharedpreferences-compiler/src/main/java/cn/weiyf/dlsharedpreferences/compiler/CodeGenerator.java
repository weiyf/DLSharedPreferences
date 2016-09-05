package cn.weiyf.dlsharedpreferences.compiler;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

import cn.weiyf.dlsharedpreferences.api.EditorHelper;
import cn.weiyf.dlsharedpreferences.api.SprefHelper;
import cn.weiyf.dlsharedpreferences.api.field.BooleanEditorField;
import cn.weiyf.dlsharedpreferences.api.field.BooleanSprefField;
import cn.weiyf.dlsharedpreferences.api.field.DefaultBoolean;
import cn.weiyf.dlsharedpreferences.api.field.DefaultFloat;
import cn.weiyf.dlsharedpreferences.api.field.DefaultInteger;
import cn.weiyf.dlsharedpreferences.api.field.DefaultLong;
import cn.weiyf.dlsharedpreferences.api.field.DefaultString;
import cn.weiyf.dlsharedpreferences.api.field.FloatEditorField;
import cn.weiyf.dlsharedpreferences.api.field.FloatSprefField;
import cn.weiyf.dlsharedpreferences.api.field.IntegerEditorField;
import cn.weiyf.dlsharedpreferences.api.field.IntegerSperfField;
import cn.weiyf.dlsharedpreferences.api.field.LongEditorField;
import cn.weiyf.dlsharedpreferences.api.field.LongSprefField;
import cn.weiyf.dlsharedpreferences.api.field.StringEditorField;
import cn.weiyf.dlsharedpreferences.api.field.StringSprefField;


public class CodeGenerator {
    private static final String CLASS_EDITOR_PREFIX = "Editor_";

    private static final String METHOD_CREATE = "create";
    private static final String METHOD_EDIT = "edit";

    private final String className;
    private final String generatedClassName;
    private final ArrayList<HashMap<Class, Element>> methodsList;

    private ClassName contextClass, spfClass, editorClass;

    public CodeGenerator(String packageName, String className, ArrayList<HashMap<Class, Element>> methodsMap) {
        this.className = className;
        this.generatedClassName = className + "_";
        this.methodsList = methodsMap;

        contextClass = ClassName.get("android.content", "Context");
        editorClass = ClassName.get(packageName + "." + generatedClassName, CLASS_EDITOR_PREFIX + className);
        spfClass = ClassName.get(packageName, generatedClassName);
    }


    public TypeSpec generateClass() {
        return TypeSpec.classBuilder(generatedClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(SprefHelper.class)
                .addMethod(construcotr())
                .addMethod(create())
                .addMethods(fieldMethods())
                .addType(classEditor())
                .addMethod(edit())
                .build();
    }

    private MethodSpec construcotr() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .addParameter(contextClass, "context")
                .addCode("super(context, $S);\n", "_" + className)
                .build();
    }

    private MethodSpec create() {
        return MethodSpec.methodBuilder(METHOD_CREATE)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(spfClass)
                .addParameter(contextClass, "context")
                .addCode("return new $T(context);\n", spfClass)
                .build();
    }

    private MethodSpec edit() {
        return MethodSpec.methodBuilder(METHOD_EDIT)
                .addModifiers(Modifier.PUBLIC)
                .returns(editorClass)
                .addCode("return new $T(getEditor());\n", editorClass)
                .build();
    }

    private Iterable<MethodSpec> fieldMethods() {
        List<MethodSpec> methodSpecs = new ArrayList<>();
        for (HashMap<Class, Element> map : methodsList) {

            Class clazz = map.keySet().iterator().next();
            Element element = map.get(clazz);
            String fieldName = element.toString();
            MethodSpec methodSpec = null;
            if (clazz == String.class) {
                methodSpec = getSpfFieldMethodSpec(fieldName, StringSprefField.class, element.getAnnotation(DefaultString.class).value());
            } else if (clazz == Integer.class) {
                methodSpec = getSpfFieldMethodSpec(fieldName, IntegerSperfField.class, element.getAnnotation(DefaultInteger.class).value());
            } else if (clazz == Long.class) {
                methodSpec = getSpfFieldMethodSpec(fieldName, LongSprefField.class, element.getAnnotation(DefaultLong.class).value());
            } else if (clazz == Float.class) {
                methodSpec = getSpfFieldMethodSpec(fieldName, FloatSprefField.class, element.getAnnotation(DefaultFloat.class).value());
            } else if (clazz == Boolean.class) {
                methodSpec = getSpfFieldMethodSpec(fieldName, BooleanSprefField.class, element.getAnnotation(DefaultBoolean.class).value());
            }
            methodSpecs.add(methodSpec);

        }
        return methodSpecs;
    }

    private MethodSpec getSpfFieldMethodSpec(String fieldName, Class clazz, String defaultValue) {
        return MethodSpec.methodBuilder(fieldName)
                .addModifiers(Modifier.PUBLIC)
                .returns(clazz)
                .addCode("return new $T(mSharedPreferences, $S, $S);\n", clazz, fieldName, defaultValue)
                .build();
    }

    private MethodSpec getSpfFieldMethodSpec(String fieldName, Class clazz, boolean defaultValue) {
        return MethodSpec.methodBuilder(fieldName)
                .addModifiers(Modifier.PUBLIC)
                .returns(clazz)
                .addCode("return new $T(mSharedPreferences, $S, $L);\n", clazz, fieldName, defaultValue)
                .build();
    }

    private MethodSpec getSpfFieldMethodSpec(String fieldName, Class clazz, float defaultValue) {
        return MethodSpec.methodBuilder(fieldName)
                .addModifiers(Modifier.PUBLIC)
                .returns(clazz)
                .addCode("return new $T(mSharedPreferences, $S, $LF);\n", clazz, fieldName, defaultValue)
                .build();
    }

    private MethodSpec getSpfFieldMethodSpec(String fieldName, Class clazz, int defaultValue) {
        return MethodSpec.methodBuilder(fieldName)
                .addModifiers(Modifier.PUBLIC)
                .returns(clazz)
                .addCode("return new $T(mSharedPreferences, $S, $L);\n", clazz, fieldName, defaultValue)
                .build();
    }

    private MethodSpec getSpfFieldMethodSpec(String fieldName, Class clazz, long defaultValue) {
        return MethodSpec.methodBuilder(fieldName)
                .addModifiers(Modifier.PUBLIC)
                .returns(clazz)
                .addCode("return new $T(mSharedPreferences, $S, $LL);\n", clazz, fieldName, defaultValue)
                .build();
    }

    private TypeSpec classEditor() {
        return TypeSpec.classBuilder(CLASS_EDITOR_PREFIX + className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(ClassName.get(EditorHelper.class))
                .addMethod(editorConstrucotr())
                .addMethods(editorFieldMethods())
                .build();
    }

    private MethodSpec editorConstrucotr() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassName.get("android.content.SharedPreferences", "Editor"), "editor")
                .addCode("super(editor);\n")
                .build();
    }

    private Iterable<MethodSpec> editorFieldMethods() {
        List<MethodSpec> methodSpecs = new ArrayList<>();

        for (HashMap<Class, Element> map : methodsList) {

            Class clazz = map.keySet().iterator().next();
            String fieldName = map.get(clazz).toString();

            Class typeClass = null;
            if (clazz == String.class) {
                typeClass = StringEditorField.class;
            } else if (clazz == Integer.class) {
                typeClass = IntegerEditorField.class;
            } else if (clazz == Long.class) {
                typeClass = LongEditorField.class;
            } else if (clazz == Float.class) {
                typeClass = FloatEditorField.class;
            } else if (clazz == Boolean.class) {
                typeClass = BooleanEditorField.class;
            }
            if (typeClass != null) {
                MethodSpec methodSpec = getEditorFieldsMethodSpec(fieldName, typeClass);
                methodSpecs.add(methodSpec);
            }
        }
        return methodSpecs;
    }

    private MethodSpec getEditorFieldsMethodSpec(String fieldName, Class clazz) {
        MethodSpec methodSpec;
        methodSpec = MethodSpec.methodBuilder(fieldName)
                .addModifiers(Modifier.PUBLIC)
                .returns(ParameterizedTypeName.get(ClassName.get(clazz), editorClass))
                .addCode("return new $T(this,$S);\n", clazz, fieldName)
                .build();
        return methodSpec;
    }
}
