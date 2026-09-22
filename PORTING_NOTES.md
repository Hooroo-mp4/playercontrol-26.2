# PlayerControl - Minecraft 26.2 port

This project is ported from the supplied PlayerControl source to the Mojang-mapped Fabric 26.2 toolchain.

## Target
- Minecraft: 26.2
- Fabric Loader: 0.19.5
- Fabric API: 0.160.0+26.2
- Carpet: 26.2+v260616
- Fabric Loom: 1.17-SNAPSHOT
- Gradle: 9.5.1
- Java: 25

## Build on Windows
1. Install a JDK 25 and make sure `java -version` reports Java 25.
2. Open this folder in a terminal.
3. Run:
   `gradlew.bat build`
4. The built jar will be under `build\\libs`.

Minecraft 26.2 uses Mojang's deobfuscated names, so Yarn mappings are intentionally not used.
