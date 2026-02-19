import com.vanniktech.maven.publish.JavaLibrary
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SourcesJar

plugins {
    `java-library`
    id("org.gradlex.extra-java-module-info") version "1.14"
    id("com.vanniktech.maven.publish") version "0.36.0"
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

extraJavaModuleInfo {
    failOnAutomaticModules = true
    module("chronicle-core-2026.3.jar", "chronicle.core")
    module("posix-2026.2.jar", "net.openhft.posix")
    module("chronicle-analytics-2026.2.jar", "chronicle.analytics")
    module("jna-platform-5.5.0.jar", "com.sun.jna.platform")
    module("jna-5.5.0.jar", "com.sun.jna")
    module("jnr-ffi-2.2.15.jar", "org.jnrproject.ffi")
    module("jnr-constants-0.10.4.jar", "org.jnrproject.constants")
    module("jffi-1.3.12.jar", "org.jnrproject.jffi")
    module("jffi-1.3.12-native.jar", "org.jnrproject.jffi.nativelibs")
    module("jnr-a64asm-1.0.0.jar", "jnr.a64asm")
    module("jnr-x86asm-1.0.2.jar", "jnr.x86asm")
}

dependencies {
    implementation("net.openhft:chronicle-core:2026.3")
    testImplementation(platform("org.junit:junit-bom:6.0.3"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

mavenPublishing {
    configure(JavaLibrary(
        javadocJar = JavadocJar.Javadoc(),
        sourcesJar = SourcesJar.Sources(),
    ))

    publishToMavenCentral()

    signAllPublications()

    coordinates("com.abissell.java-util", "java-util", "0.8.0-SNAPSHOT")

    pom {
        name.set("java-util")
        description.set("A set of basic Java utilities")
        inceptionYear.set("2023")
        url.set("https://github.com/abissell/java-util")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("abissell")
                name.set("Andrew Bissell")
                email.set("abissell@gmail.com")
                url.set("https://www.abissell.com")
            }
        }
        scm {
            connection.set("scm:git:git@github.com:abissell/java-util.git")
            developerConnection.set("scm:git:ssh://github.com:abissell/java-util.git")
            url.set("https://github.com/abissell/java-util/tree/main")
        }
    }
}
