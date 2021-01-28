import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("io.kotest") version "0.2.6"
	id("org.springframework.boot") version "2.4.1"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("com.vaadin") version "0.8.0"
	kotlin("jvm") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
	kotlin("plugin.jpa") version "1.4.21"
}

group = "pl.gr16"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8
val kotestV = "4.2.6"

repositories {
	mavenCentral()
	jcenter()
}

extra["vaadinVersion"] = "14.4.4"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation ("org.jetbrains.exposed:exposed-core:0.28.1")
	implementation ("org.jetbrains.exposed:spring-transaction:0.28.1")
	implementation ("org.jetbrains.exposed:exposed-java-time:0.28.1")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.vaadin:vaadin-spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.github.mvysny.karibudsl:karibu-dsl:1.0.4")
	runtimeOnly("com.h2database:h2:1.4.196")
//	testImplementation("org.springframework.boot:spring-boot-starter-test") {
//		exclude("org.junit.vintage", "junit-vintage-engine")
//	}
	testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestV")
	testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestV")
	testImplementation("io.kotest:kotest-assertions-arrow-jvm:$kotestV")
	testImplementation("io.kotest:kotest-property-jvm:$kotestV")
	testImplementation("io.kotest:kotest-extensions-spring:$kotestV")
}

dependencyManagement {
	imports {
		mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions.jvmTarget = "1.8"

tasks.withType<Test> {
	useJUnitPlatform()
}
