## java agent practice

### how to debug 
使用idea,
 agent_test:build.gradle
 ```
  
plugins {
    id 'application'
}
application {
    mainClassName = 'org.example.Main'
    applicationDefaultJvmArgs = [
            '-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=6789',
            "-javaagent:${project(':premain').projectDir}/build/libs/premain-1.0-SNAPSHOT.jar",
    ]
}

 ```
![png](asset/2023-04-20-10-35-28.png)