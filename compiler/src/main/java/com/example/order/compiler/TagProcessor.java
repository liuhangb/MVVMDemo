package com.example.order.compiler;

import com.example.order.annotation.TAG;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.example.order.annotation.TAG")
public class TagProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(TAG.class);
        Iterator<? extends Element> iterator = elementsAnnotatedWith.iterator();
        while (iterator.hasNext()) {
            Element next = iterator.next();
            if (next == null) continue;
            String suffix = next.getSimpleName().toString();
            generateClass(suffix);
        }
        return true;
    }

    private void generateClass(String suffix) {
        FieldSpec fieldSpec = FieldSpec
                .builder(String.class, "TAG", Modifier.PUBLIC, Modifier.STATIC)
                .initializer("\"" + suffix + "\"", String.class)
                .build();

        TypeSpec typeSpec = TypeSpec.classBuilder("Tag" + suffix)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(fieldSpec)
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.order.mvvmdemo.tag", typeSpec)
                .build();
        try {
            Filer filer = processingEnv.getFiler();
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
