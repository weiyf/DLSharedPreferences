package cn.weiyf.dlsharedpreferences.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import cn.weiyf.dlsharedpreferences.api.Spref;


/**
 * Created by weiyf on 2016/7/4.
 */

@AutoService(Processor.class)
public class SprefProcessor extends AbstractProcessor {

    private Filer mFiler;

    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Spref.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Spref.class)) {
            TypeElement annotatedClass = (TypeElement) element;
            generateCode(annotatedClass);
        }
        return false;
    }

    private void generateCode(TypeElement annotatedClass) {
        String packageName = getPackageName(annotatedClass);

        ArrayList<HashMap<Class, Element>> methodsList = new ArrayList<>();
        for (Element element : annotatedClass.getEnclosedElements()) {
            HashMap<Class, Element> hashMap = new HashMap<>();
            if (element.getKind() == ElementKind.FIELD) {
                TypeKind typeKind = element.asType().getKind();
                if (typeKind == TypeKind.INT) {
                    hashMap.put(Integer.class, element);
                } else if (typeKind == TypeKind.LONG) {
                    hashMap.put(Long.class, element);
                } else if (typeKind == TypeKind.FLOAT) {
                    hashMap.put(Float.class, element);
                } else if (typeKind == TypeKind.BOOLEAN) {
                    hashMap.put(Boolean.class, element);
                } else if (typeKind == TypeKind.DECLARED) {
                    String type = element.asType().toString();
                    if (type.equals(String.class.getName())) {
                        hashMap.put(String.class, element);
                    } else if (type.equals(Integer.class.getName())) {
                        hashMap.put(Integer.class, element);
                    } else if (type.equals(Long.class.getName())) {
                        hashMap.put(Long.class, element);
                    } else if (type.equals(Float.class.getName())) {
                        hashMap.put(Float.class, element);
                    } else if (type.equals(Boolean.class.getName())) {
                        hashMap.put(Boolean.class, element);
                    } else {
                        errorField(annotatedClass);
                    }
                } else {
                    errorField(annotatedClass);
                }
            }
            if (!hashMap.isEmpty()) {
                methodsList.add(hashMap);
            }
        }
        CodeGenerator codeGenerator = new CodeGenerator(
                packageName, annotatedClass.getSimpleName().toString(), methodsList);
        TypeSpec generatedClass = codeGenerator.generateClass();
        JavaFile javaFile = JavaFile.builder(packageName, generatedClass).build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void errorField(TypeElement typeElement) {
        error(typeElement, "Fields of classes annotated with %s must be String/int/long/float/boolean.", "@Spref");
    }

    private void error(Element element, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message, element);
    }

    private String getPackageName(TypeElement typeElement) {
        return mElementUtils.getPackageOf(typeElement).getQualifiedName().toString();
    }
}
