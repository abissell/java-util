import com.vanniktech.maven.publish.JavaLibrary
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SourcesJar

plugins {
    `java-library`
    id("com.vanniktech.maven.publish") version "0.36.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.openhft:chronicle-core:2026.3")
    testImplementation(platform("org.junit:junit-bom:6.0.3"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test>().configureEach {
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
