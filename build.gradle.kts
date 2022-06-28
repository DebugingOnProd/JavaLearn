plugins {
    id ("java")
    id ("java-library")
    id ("maven-publish")
    id ("application")
    id ("org.openjfx.javafxplugin") version "0.0.13"
    id ("org.beryx.jlink") version "2.24.4"

}
group "org.lhq"
version "1.0"

repositories {
    mavenLocal()
    maven { url=uri("https://maven.aliyun.com/repository/public/") }
    maven { url=uri("https://maven.aliyun.com/repository/spring/") }
    mavenCentral()
}
javafx {
    version = "17.0.1"
    modules  ( "javafx.controls", "javafx.fxml" )
}
application{
    mainClass.set("org.lhq.RpcServerBoot")
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}



dependencies {
    implementation ("org.dom4j:dom4j:2.1.3")
    implementation ("xerces:xercesImpl:2.12.2")
    implementation ("io.netty:netty-all:4.1.77.Final")
    implementation ("cn.hutool:hutool-all:5.8.2")
    implementation ("com.alibaba:easyexcel:3.1.0")
    compileOnly ("org.projectlombok:lombok:1.18.24")
    testCompileOnly ("org.projectlombok:lombok:1.18.24")
    annotationProcessor ("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.24")
    implementation ("com.google.guava:guava:31.1-jre")
    implementation ("com.lmax:disruptor:3.4.4")
    implementation ("ch.qos.logback:logback-classic:1.2.11")
    implementation ("io.vavr:vavr:1.0.0-alpha-4")
    implementation ("org.springframework:spring:5.3.20")
    implementation ("org.springframework:spring-beans:5.3.20")
    implementation ("org.springframework:spring-context:5.3.20")
    implementation ("io.projectreactor:reactor-core:3.4.18")
    implementation ("org.springframework:spring:5.3.20")
    implementation ("org.springframework:spring-beans:5.3.20")
    implementation ("org.springframework:spring-context:5.3.20")
    implementation ("org.jfxtras:jmetro:11.6.15")
    implementation ("org.openjfx:javafx-controls:17.0.1")
    implementation ("org.openjfx:javafx-fxml:17.0.1")
    implementation ("com.github.almasb:fxgl:17.1")
    implementation ("cglib:cglib:3.3.0")
    implementation ("com.101tec:zkclient:0.11")
    implementation ("org.apache.zookeeper:zookeeper:3.8.0")
    implementation ("org.apache.commons:commons-lang3:3.12.0")
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("mysql:mysql-connector-java:8.0.29")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}




