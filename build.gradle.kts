import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("org.jetbrains.kotlin.jvm") version "1.3.72" // == kotlin("jvm") version "1.3.72" apply false
	id("org.jetbrains.kotlin.plugin.spring") version "1.3.72" // == kotlin("plugin.spring") version "1.3.72" apply false
}

allprojects{
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")


	group = "com.example"
	version = "0.0.1-SNAPSHOT"

	java {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("org.springframework.kafka:spring-kafka")
		testImplementation("org.springframework.boot:spring-boot-starter-test") {
			exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		}
		testImplementation("org.springframework.kafka:spring-kafka-test")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}
}


dependencies{
	implementation(project(":subprojects:ui"))
	implementation(project(":subprojects:domain"))
	implementation(project(":subprojects:infrastructure"))
}

project(":subprojects:ui"){
	dependencies{
		implementation(project(":subprojects:infrastructure"))
		implementation(project(":subprojects:domain"))
	}
}

project(":subprojects:infrastructure"){
	dependencies{
		implementation(project(":subprojects:domain"))
	}
}
